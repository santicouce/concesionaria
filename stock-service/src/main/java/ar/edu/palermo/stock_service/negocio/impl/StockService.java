package ar.edu.palermo.stock_service.negocio.impl;

import ar.edu.palermo.stock_service.cliente.SucursalClient;
import ar.edu.palermo.stock_service.cliente.VehiculoClient;
import ar.edu.palermo.stock_service.dominio.Stock;
import ar.edu.palermo.stock_service.negocio.IStockService;
import ar.edu.palermo.stock_service.repositorio.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService implements IStockService {

    private final StockRepository stockRepository;
    private final SucursalClient sucursalClient;
    private final VehiculoClient vehiculoClient;

    @Autowired
    public StockService(
            StockRepository stockRepository,
            SucursalClient sucursalClient,
            VehiculoClient vehiculoClient) {
        this.stockRepository = stockRepository;
        this.sucursalClient = sucursalClient;
        this.vehiculoClient = vehiculoClient;
    }

    @Override
    public Stock guardar(Stock stock) {
        if (!sucursalClient.existeSucursal(stock.getSucursalId())) {
            throw new RuntimeException("Sucursal no encontrada");
        }
        if (!vehiculoClient.existeVehiculo(stock.getVehiculoId())) {
            throw new RuntimeException("Vehículo no encontrado");
        }
        return stockRepository.save(stock);
    }

    @Override
    public List<Stock> obtenerTodos() {
        return stockRepository.findAll();
    }

    @Override
    public Optional<Stock> obtenerPorId(Integer id) {
        return stockRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        stockRepository.deleteById(id);
    }

    @Override
    public Optional<Stock> buscarPorSucursalYVehiculo(Integer sucursalId, Integer vehiculoId) {
        return stockRepository.findBySucursalIdAndVehiculoId(sucursalId, vehiculoId);
    }
}
