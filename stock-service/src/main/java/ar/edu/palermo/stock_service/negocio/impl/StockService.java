package ar.edu.palermo.stock_service.negocio.impl;

import ar.edu.palermo.stock_service.cliente.SucursalClient;
import ar.edu.palermo.stock_service.cliente.VehiculoClient;
import ar.edu.palermo.stock_service.dominio.Stock;
import ar.edu.palermo.stock_service.dto.StockDTO;
import ar.edu.palermo.stock_service.negocio.IStockService;
import ar.edu.palermo.stock_service.repositorio.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.palermo.stock_service.exceptions.InvalidRequestException;
import ar.edu.palermo.stock_service.exceptions.SucursalNotFoundException;
import ar.edu.palermo.stock_service.exceptions.VehiculoNotFoundException;

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
    public StockDTO guardar(StockDTO stock) {
        if (!sucursalClient.existeSucursal(stock.getSucursalId())) {
            throw new SucursalNotFoundException("La sucursal con ID " + stock.getSucursalId() + " no existe");
        }
        if (!vehiculoClient.existeVehiculo(stock.getVehiculoId())) {
            throw new VehiculoNotFoundException("El vehículo con ID " + stock.getVehiculoId() + " no existe");
        }
        Stock stockEntity = new Stock(
                stock.getSucursalId(),
                stock.getVehiculoId(),
                stock.getCantidad()
                );
        stockRepository.save(stockEntity);
        stock.setId(stockEntity.getId());
        return stock;
    }

    @Override
    public List<StockDTO> obtenerTodos() {
        return stockRepository.findAll()
            .stream()
            .map(stock -> new StockDTO(
                stock.getId(),
                stock.getSucursalId(),
                stock.getVehiculoId(),
                stock.getCantidad()))
            .toList();
    }

    @Override
    public Optional<StockDTO> obtenerPorId(Integer id) {
        return stockRepository.findById(id)
            .map(stock -> new StockDTO(
                stock.getId(),
                stock.getSucursalId(),
                stock.getVehiculoId(),
                stock.getCantidad()));
    }

    @Override
    public void eliminar(Integer id) {
        stockRepository.deleteById(id);
    }

    @Override
    public Optional<StockDTO> buscarPorSucursalYVehiculo(Integer sucursalId, Integer vehiculoId) {
        return stockRepository.findBySucursalIdAndVehiculoId(sucursalId, vehiculoId)
            .map(stock -> new StockDTO(
                stock.getId(),
                stock.getSucursalId(),
                stock.getVehiculoId(),
                stock.getCantidad()));
    }

    @Override
    public Optional<StockDTO> decrementarStock(Integer stockId) {
        return stockRepository.findById(stockId)
            .map(stock -> {
                int cantidadActual = stock.getCantidad();
                if (cantidadActual <= 0) {
                    throw new InvalidRequestException(
                        "No hay stock suficiente para realizar la venta (stockId=" + stockId + ")");
                }
                stock.setCantidad(cantidadActual - 1);
                return stockRepository.save(stock);
            })
            .map(actualizado -> new StockDTO(
                actualizado.getId(),
                actualizado.getSucursalId(),
                actualizado.getVehiculoId(),
                actualizado.getCantidad()
            ));
    }
}
