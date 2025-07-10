package ar.edu.palermo.stock_service.controlador;

import ar.edu.palermo.stock_service.cliente.SucursalClient;
import ar.edu.palermo.stock_service.dto.StockDTO;
import ar.edu.palermo.stock_service.exceptions.InvalidRequestException;
import ar.edu.palermo.stock_service.negocio.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockControlador {

    private final IStockService stockService;
    private final SucursalClient sucursalClient;

    @Autowired
    public StockControlador(IStockService stockService,
                            SucursalClient sucursalClient) {
        this.stockService = stockService;
        this.sucursalClient = sucursalClient;
    }

    /**
     * GET /stock
     * - Si vienen ambos query params (sucursalId y vehiculoId), busca esa combinación.
     * - Si no vienen, devuelve todos los stocks.
     * - Si viene sólo uno de los dos, lanza BadRequest.
     */
    @GetMapping
    public List<StockDTO> obtenerStock(
            @RequestParam(required = false) Integer sucursalId,
            @RequestParam(required = false) Integer vehiculoId) {

        boolean tieneSucursal = sucursalId != null;
        boolean tieneVehiculo = vehiculoId != null;

        if (tieneSucursal ^ tieneVehiculo) {
            throw new InvalidRequestException(
                "Debe indicar ambos parámetros 'sucursalId' y 'vehiculoId', o ninguno");
        }

        if (tieneSucursal) {
            // Si no existe, NotFoundException
            StockDTO dto = stockService.buscarPorSucursalYVehiculo(sucursalId, vehiculoId)
                .orElseThrow(() -> new InvalidRequestException(
                    "Stock no encontrado para sucursal " + sucursalId +
                    " y vehículo " + vehiculoId));
            return List.of(dto);
        }

        return stockService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public StockDTO obtenerPorId(@PathVariable Integer id) {
        return stockService.obtenerPorId(id)
            .orElseThrow(() -> new InvalidRequestException("Stock con id " + id + " no encontrado"));
    }

    @PostMapping
    public StockDTO crear(@RequestBody StockDTO stockRequest) {
        return stockService.guardar(stockRequest);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        stockService.eliminar(id);
    }

    @GetMapping("/central")
    public StockDTO obtenerStockCentral(@RequestParam Integer vehiculoId) {
        Integer sucursalCentralId = sucursalClient.obtenerIdSucursalCentral();
        return stockService.buscarPorSucursalYVehiculo(sucursalCentralId, vehiculoId)
            .orElseThrow(() -> new InvalidRequestException(
                "Stock central no encontrado para vehículo " + vehiculoId));
    }

    @PostMapping("/{id}/venta")
    public void venderUnidad(@PathVariable Integer id) {
        stockService.decrementarStock(id)
            .orElseThrow(() -> new InvalidRequestException(
                "No hay unidades disponibles"));
    }
}
