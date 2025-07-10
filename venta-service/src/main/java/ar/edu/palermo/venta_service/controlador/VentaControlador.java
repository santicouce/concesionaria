package ar.edu.palermo.venta_service.controlador;

import ar.edu.palermo.venta_service.dto.VentaDTO;
import ar.edu.palermo.venta_service.exceptions.NegocioException;
import ar.edu.palermo.venta_service.negocio.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ventas")
public class VentaControlador {

    private final IVentaService ventaService;

    @Autowired
    public VentaControlador(IVentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public List<VentaDTO> obtenerTodas() {
        return ventaService.obtenerTodas();
    }

    @PostMapping
    public ResponseEntity<Void> crearVenta(@RequestBody VentaDTO request) {
        ventaService.crearVenta(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public Optional<VentaDTO> obtenerPorId(@PathVariable Integer id) {
        return ventaService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        ventaService.eliminar(id);
    }
    @GetMapping("/vehiculo/{idVehiculo}")
    public VentaDTO obtenerPorVehiculo(@PathVariable Integer idVehiculo) {
        Optional<VentaDTO> opventa = ventaService.obtenerPorVehiculo(idVehiculo);
        System.out.println("Buscando venta para vehículo con ID: " + idVehiculo);
        if (opventa.isPresent()) {
            return opventa.get();
        } else {
            throw new NegocioException("Venta no encontrada para el vehículo con ID: " + idVehiculo);
        }
    }
}
