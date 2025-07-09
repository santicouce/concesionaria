package ar.edu.palermo.stock_service.controlador;

import ar.edu.palermo.stock_service.dominio.Stock;
import ar.edu.palermo.stock_service.negocio.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stock")
public class StockControlador {

    private final IStockService stockService;

    @Autowired
    public StockControlador(IStockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public List<Stock> obtenerTodos() {
        return stockService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Stock> obtenerPorId(@PathVariable Integer id) {
        return stockService.obtenerPorId(id);
    }

    @PostMapping
    public Stock crear(@RequestBody Stock stock) {
        return stockService.guardar(stock);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        stockService.eliminar(id);
    }

    @GetMapping("/buscar")
    public Optional<Stock> buscarPorSucursalYVehiculo(
            @RequestParam Integer sucursalId,
            @RequestParam Integer vehiculoId) {
        return stockService.buscarPorSucursalYVehiculo(sucursalId, vehiculoId);
    }
}
