package ar.edu.palermo.stock_service.negocio;
import ar.edu.palermo.stock_service.dto.StockDTO;

import java.util.List;
import java.util.Optional;

public interface IStockService {

    StockDTO guardar(StockDTO stock);

    List<StockDTO> obtenerTodos();

    Optional<StockDTO> obtenerPorId(Integer id);

    void eliminar(Integer id);

    Optional<StockDTO> buscarPorSucursalYVehiculo(Integer sucursalId, Integer vehiculoId);
    
    Optional<StockDTO> decrementarStock(Integer stockId);
}
