package ar.edu.palermo.stock_service.negocio;

import ar.edu.palermo.stock_service.dominio.Stock;

import java.util.List;
import java.util.Optional;

public interface IStockService {

    Stock guardar(Stock stock);

    List<Stock> obtenerTodos();

    Optional<Stock> obtenerPorId(Integer id);

    void eliminar(Integer id);

    Optional<Stock> buscarPorSucursalYVehiculo(Integer sucursalId, Integer vehiculoId);
}
