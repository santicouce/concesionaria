package ar.edu.palermo.concesionaria.negocio.impl;

import ar.edu.palermo.concesionaria.dominio.Stock;
import ar.edu.palermo.concesionaria.dominio.Venta;
import ar.edu.palermo.concesionaria.exceptions.NegocioException;
import ar.edu.palermo.concesionaria.negocio.IServicioEntrega;
import ar.edu.palermo.concesionaria.negocio.IVentaService;
import ar.edu.palermo.concesionaria.repositorio.StockRepository;
import ar.edu.palermo.concesionaria.repositorio.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService implements IVentaService {

    private final VentaRepository ventaRepository;
    private final StockRepository stockRepository;
    private final IServicioEntrega servicioEntrega;

    @Autowired
    public VentaService(
            VentaRepository ventaRepository,
            StockRepository stockRepository,
            IServicioEntrega servicioEntrega) {
        this.ventaRepository = ventaRepository;
        this.stockRepository = stockRepository;
        this.servicioEntrega = servicioEntrega;
    }

    @Override
    public Venta guardar(Venta venta) {
        // Validar existencia de stock
        Optional<Stock> stock = stockRepository.findBySucursalAndVehiculo(venta.getSucursal(), venta.getVehiculo());

        boolean hayStockLocal = stock.isPresent() && stock.get().getCantidad() > 0;
        boolean hayStockCentral = venta.getSucursal().getDiasEntregaDesdeCentral() != null;

        if (!hayStockLocal && !hayStockCentral) {
            throw new NegocioException("No hay stock disponible ni en sucursal ni en central.");
        }

        // Evitar ventas duplicadas (mismo cliente, vehículo y día)
        List<Venta> ventasDelCliente = ventaRepository.findByCliente(venta.getCliente());
        boolean yaVendidaHoy = ventasDelCliente.stream().anyMatch(v ->
            v.getVehiculo().getId().equals(venta.getVehiculo().getId()) &&
            v.getFecha().equals(venta.getFecha())
        );
        if (yaVendidaHoy) {
            throw new NegocioException("Ya existe una venta para este vehículo y cliente en la misma fecha.");
        }

        // Calcular días estimados de entrega
        Integer dias = servicioEntrega.calcularTiempoEntrega(venta.getSucursal(), venta.getVehiculo());
        venta.setDiasEntrega(dias);

        // Descontar stock si hay local
        if (hayStockLocal) {
            Stock s = stock.get();
            s.setCantidad(s.getCantidad() - 1);
            stockRepository.save(s);
        }

        return ventaRepository.save(venta);
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
