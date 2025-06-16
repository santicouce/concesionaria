package ar.edu.palermo.concesionaria.negocio.impl;

import ar.edu.palermo.concesionaria.dominio.Sucursal;
import ar.edu.palermo.concesionaria.dominio.Vehiculo;
import ar.edu.palermo.concesionaria.dominio.Stock;
import ar.edu.palermo.concesionaria.negocio.IServicioEntrega;
import ar.edu.palermo.concesionaria.repositorio.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicioEntrega implements IServicioEntrega {
    // Este servicio calcula el tiempo de entrega de un vehículo basado en el stock
    
    private final StockRepository stockRepository;

    @Autowired
    public ServicioEntrega(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Integer calcularTiempoEntrega(Sucursal sucursal, Vehiculo vehiculo) {
        Optional<Stock> stock = stockRepository.findBySucursalAndVehiculo(sucursal, vehiculo);

        if (stock.isPresent() && stock.get().getCantidad() > 0) {
            return sucursal.getDiasEntregaCliente();
        } else {
            return sucursal.getDiasEntregaDesdeCentral() + sucursal.getDiasEntregaCliente();
        }
    }
}