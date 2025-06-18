package ar.edu.palermo.concesionaria.negocio.impl;

import ar.edu.palermo.concesionaria.dominio.Cliente;
import ar.edu.palermo.concesionaria.dominio.Empleado;
import ar.edu.palermo.concesionaria.dominio.Stock;
import ar.edu.palermo.concesionaria.dominio.Vehiculo;
import ar.edu.palermo.concesionaria.dominio.Venta;
import ar.edu.palermo.concesionaria.dto.VentaRequest;
import ar.edu.palermo.concesionaria.exceptions.DatosInvalidosException;
import ar.edu.palermo.concesionaria.exceptions.NegocioException;
import ar.edu.palermo.concesionaria.negocio.IVentaService;
import ar.edu.palermo.concesionaria.repositorio.ClienteRepository;
import ar.edu.palermo.concesionaria.repositorio.EmpleadoRepository;
import ar.edu.palermo.concesionaria.repositorio.StockRepository;
import ar.edu.palermo.concesionaria.repositorio.VehiculoRepository;
import ar.edu.palermo.concesionaria.repositorio.VentaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService implements IVentaService {

    private final ClienteRepository clienteRepository;
    private final VehiculoRepository vehiculoRepository;
    private final EmpleadoRepository empleadoRepository;
    private final VentaRepository ventaRepository;
    private final StockRepository stockRepository;
    private final ServicioEntrega servicioEntrega;


    @Autowired
    public VentaService(
            ClienteRepository clienteRepository,
            VehiculoRepository vehiculoRepository,
            EmpleadoRepository empleadoRepository,
            VentaRepository ventaRepository,
            StockRepository stockRepository,
            ServicioEntrega servicioEntrega
    ) {
        this.clienteRepository = clienteRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.empleadoRepository = empleadoRepository;
        this.ventaRepository = ventaRepository;
        this.stockRepository = stockRepository;
        this.servicioEntrega = servicioEntrega;
    }

    @Override
    public void crearVenta(VentaRequest request) {
        // Obtener entidades relacionadas
        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new DatosInvalidosException("Cliente no encontrado"));
        Vehiculo vehiculo = vehiculoRepository.findById(request.getVehiculoId())
                .orElseThrow(() -> new DatosInvalidosException("Vehículo no encontrado"));
        Empleado empleado = empleadoRepository.findById(request.getEmpleadoId())
                .orElseThrow(() -> new DatosInvalidosException("Empleado no encontrado"));
        
        // Validar existencia de stock
        Optional<Stock> stock = stockRepository.findBySucursalAndVehiculo(empleado.getSucursal(), vehiculo);

        boolean hayStockLocal = stock.isPresent() && stock.get().getCantidad() > 0;
        boolean hayStockCentral = empleado.getSucursal().getDiasEntregaDesdeCentral() != null;

        if (!hayStockLocal && !hayStockCentral) {
            throw new NegocioException("No hay stock disponible ni en sucursal ni en central.");
        }

        // Evitar ventas duplicadas (mismo cliente, vehículo y día)
        List<Venta> ventasDelCliente = ventaRepository.findByCliente(cliente);
        boolean yaVendidaHoy = ventasDelCliente.stream().anyMatch(v ->
            v.getVehiculo().getId().equals(vehiculo.getId()) &&
            v.getFecha().equals(request.getFecha())
        );
        if (yaVendidaHoy) {
            throw new NegocioException("Ya existe una venta para este vehículo y cliente en la misma fecha.");
        }

        // Calcular días estimados de entrega
        Integer diasDeEntrega = servicioEntrega.calcularTiempoEntrega(empleado.getSucursal(), vehiculo);

        // Descontar stock si hay local
        if (hayStockLocal) {
            Stock s = stock.get();
            s.setCantidad(s.getCantidad() - 1);
            stockRepository.save(s);
        }
        Venta venta = new Venta(cliente, empleado, vehiculo, request.getFecha(), request.getMonto(), diasDeEntrega);
        ventaRepository.save(venta);

    }


    @Override
    public List<Venta> obtenerTodas() {
        return ventaRepository.findAll();
    }

    @Override
    public Optional<Venta> obtenerPorId(Integer id) {
        return ventaRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        ventaRepository.deleteById(id);
    }
}
