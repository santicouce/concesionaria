package ar.edu.palermo.stock_service.controlador;

import ar.edu.palermo.stock_service.cliente.SucursalClient;
import ar.edu.palermo.stock_service.dominio.Stock;
import ar.edu.palermo.stock_service.exceptions.InvalidRequestException;
import ar.edu.palermo.stock_service.negocio.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    /**
     * GET /stock
     * - Si vienen ambos query params (sucursalId y vehiculoId), busca esa combinación.
     * - Si no vienen, devuelve todos los stocks.
     * - Si viene solo uno de los dos, responde 400 Bad Request.
     */
    @GetMapping
    public ResponseEntity<?> obtenerStock(
            @RequestParam(required = false) Integer sucursalId,
            @RequestParam(required = false) Integer vehiculoId) {

        boolean tieneSucursal = sucursalId != null;
        boolean tieneVehiculo = vehiculoId != null;

        if (tieneSucursal && tieneVehiculo) {
            Optional<Stock> opt = stockService.buscarPorSucursalYVehiculo(sucursalId, vehiculoId);
            return opt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
        } else if (!tieneSucursal && !tieneVehiculo) {
            List<Stock> todos = stockService.obtenerTodos();
            return ResponseEntity.ok(todos);
        } else {
            return ResponseEntity
                .badRequest()
                .body("Debe indicar ambos parámetros 'sucursalId' y 'vehiculoId' para la búsqueda específica.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> obtenerPorId(@PathVariable Integer id) {
        return stockService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Stock> crear(@RequestBody Stock stock) {
        Stock creado = stockService.guardar(stock);
        return ResponseEntity.ok(creado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        stockService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/central")
    // Dado un id de vehiculo, verifico el stock para en la sucursal central
    public ResponseEntity<Stock> obtenerStockCentral(@RequestParam Integer vehiculoId) {
        // Obtengo sucursal central enviando request al servicio de sucursal
        SucursalClient sucursalClient = new SucursalClient();
        Integer sucursalCentralId = sucursalClient.obtenerIdSucursalCentral();

        Optional<Stock> opt = stockService.buscarPorSucursalYVehiculo(sucursalCentralId, vehiculoId);
        return opt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/venta")
    public ResponseEntity<Void> venderUnidad(@PathVariable Integer id) {
        try {
            Optional<Stock> opt = stockService.decrementarStock(id);
            if (opt.isPresent()) {
                // Venta exitosa
                return ResponseEntity.ok().build();
            } else {
                // No existe ese stock
                return ResponseEntity.notFound().build();
            }
        } catch (InvalidRequestException ex) {
            // Error
            return ResponseEntity.badRequest().build();
        }
    }

}
