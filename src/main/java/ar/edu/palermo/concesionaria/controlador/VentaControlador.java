package ar.edu.palermo.concesionaria.controlador;

import ar.edu.palermo.concesionaria.dominio.Venta;
import ar.edu.palermo.concesionaria.dto.VentaRequest;
import ar.edu.palermo.concesionaria.negocio.IVentaService;
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
    public List<Venta> obtenerTodas() {
        return ventaService.obtenerTodas();
    }

    @PostMapping
    public ResponseEntity<Void> crearVenta(@RequestBody VentaRequest request) {
        ventaService.crearVenta(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public Optional<Venta> obtenerPorId(@PathVariable Integer id) {
        return ventaService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        ventaService.eliminar(id);
    }
}
