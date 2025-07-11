package ar.edu.palermo.venta_service.negocio.impl;

import ar.edu.palermo.venta_service.cliente.ClienteClient;
import ar.edu.palermo.venta_service.cliente.EmpleadoClient;
import ar.edu.palermo.venta_service.cliente.StockClient;
import ar.edu.palermo.venta_service.cliente.VehiculoClient;
import ar.edu.palermo.venta_service.dominio.Venta;
import ar.edu.palermo.venta_service.dto.StockInfoDTO;
import ar.edu.palermo.venta_service.dto.SucursalInfoDTO;
import ar.edu.palermo.venta_service.dto.VentaDTO;
import ar.edu.palermo.venta_service.exceptions.ObjetoRelacionadoNoEncontradoException;
import ar.edu.palermo.venta_service.exceptions.NegocioException;
import ar.edu.palermo.venta_service.negocio.IVentaService;
import ar.edu.palermo.venta_service.repositorio.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService implements IVentaService {

    private final VentaRepository ventaRepository;
    private final ClienteClient clienteClient;
    private final EmpleadoClient empleadoClient;
    private final VehiculoClient vehiculoClient;
    private final StockClient stockClient;
    private final ServicioEntrega servicioEntrega;

    @Autowired
    public VentaService(
            VentaRepository ventaRepository,
            ClienteClient clienteClient,
            EmpleadoClient empleadoClient,
            VehiculoClient vehiculoClient,
            StockClient stockClient,
            ServicioEntrega servicioEntrega
    ) {
        this.ventaRepository = ventaRepository;
        this.clienteClient = clienteClient;
        this.empleadoClient = empleadoClient;
        this.vehiculoClient = vehiculoClient;
        this.stockClient = stockClient;
        this.servicioEntrega = servicioEntrega;
    }

    @Override
    public void crearVenta(VentaDTO request) {
        // Validar existencia de entidades externas
        if (!clienteClient.existeCliente(request.getClienteId())) {
            throw new ObjetoRelacionadoNoEncontradoException("Cliente no encontrado");
        }
        if (!vehiculoClient.existeVehiculo(request.getVehiculoId())) {
            throw new ObjetoRelacionadoNoEncontradoException("Vehículo no encontrado");
        }
        if (!empleadoClient.existeEmpleado(request.getEmpleadoId())) {
            throw new ObjetoRelacionadoNoEncontradoException("Empleado no encontrado");
        }

        if (ventaRepository.findByVehiculoId(request.getVehiculoId()).isPresent()) {
            throw new NegocioException("El vehículo ya ha sido vendido.");
        }

        // Validar existencia de stock
        SucursalInfoDTO sucursalDeVenta = empleadoClient.obtenerSucursal(request.getEmpleadoId());
        Integer sucursalDeVentaId = sucursalDeVenta.getId();
        System.out.println("Sucursal de venta: " + sucursalDeVentaId);
        System.out.println("Vehículo ID: " + request.getVehiculoId());
        StockInfoDTO stockEnSucursalEmpleado = stockClient.findBySucursalAndVehiculo(request.getVehiculoId(), sucursalDeVentaId);
        System.out.println("id Stock en sucursal del empleado: " + stockEnSucursalEmpleado.getId());
        
        StockInfoDTO stockEnSucursalCentral = stockClient.findByVehiculoInCentral(request.getVehiculoId());
        boolean esVentaDesDeCentral = sucursalDeVenta.getEsCentral();
        boolean sinStockCentral = stockEnSucursalCentral.getCantidad() <= 0;
        boolean stockProvieneDeCentral = false;
        if (esVentaDesDeCentral) {
            System.out.println("Venta realizada desde la sucursal central.");
            stockProvieneDeCentral = true;
            if (sinStockCentral) {
                System.out.println("No hay stock en la sucursal central.");
                throw new NegocioException("No hay stock disponible.");
            }
        } else {
            System.out.println("Venta realizada desde la sucursal: " + sucursalDeVenta.getId());
            System.out.println("Stock en sucursal: " + stockEnSucursalEmpleado.getCantidad());
            boolean sinStockSucursal = stockEnSucursalEmpleado.getCantidad() <= 0;
            if (sinStockSucursal && sinStockCentral) {
                throw new NegocioException("No hay stock disponible.");
            }
            if (sinStockSucursal && !sinStockCentral) {
                System.out.println("No hay stock en la sucursal, se tomará de la central.");
                stockProvieneDeCentral = true;
            }
        }

        // Calcular días estimados de entrega
        Integer diasDeEntrega = servicioEntrega.calcularTiempoEntrega(sucursalDeVenta, stockEnSucursalEmpleado);
        System.out.println("Días estimados de entrega: " + diasDeEntrega);
        // Descontar stock
        if (!stockProvieneDeCentral) {
            Integer stockId = stockEnSucursalEmpleado.getId();
            System.out.println("Descontando stock en sucursal: " + stockId);
            stockClient.decrementStock(stockId);
        } else {
            Integer stockId = stockEnSucursalCentral.getId();
            System.out.println("Descontando stock en sucursal central" + stockId);
            stockClient.decrementStock(stockId);
        }

        Venta venta = new Venta(
                request.getClienteId(),
                request.getEmpleadoId(),
                request.getVehiculoId(),
                request.getFecha(),
                request.getMonto(),
                diasDeEntrega
        );
        ventaRepository.save(venta);
    }

    @Override
    public List<VentaDTO> obtenerTodas() {
        return ventaRepository.findAll()
                .stream()
                .map(venta -> new VentaDTO(
                        venta.getClienteId(),
                        venta.getVehiculoId(),
                        venta.getEmpleadoId(),
                        venta.getFecha(),
                        venta.getMonto()
                ))
                .toList();
    }

    @Override
    public Optional<VentaDTO> obtenerPorId(Integer id) {
        return ventaRepository.findById(id)
                .map(venta -> new VentaDTO(
                        venta.getClienteId(),
                        venta.getVehiculoId(),
                        venta.getEmpleadoId(),
                        venta.getFecha(),
                        venta.getMonto()
                ));
    }

    @Override
    public Optional<VentaDTO> obtenerPorVehiculo(Integer idVehiculo) {
        return ventaRepository.findByVehiculoId(idVehiculo)
                .map(venta -> new VentaDTO(
                        venta.getClienteId(),
                        venta.getVehiculoId(),
                        venta.getEmpleadoId(),
                        venta.getFecha(),
                        venta.getMonto()
                ));
    }

    @Override
    public void eliminar(Integer id) {
        ventaRepository.deleteById(id);
    }
}
