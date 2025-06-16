package ar.edu.palermo.concesionaria.controlador;

import ar.edu.palermo.concesionaria.dominio.Venta;
import ar.edu.palermo.concesionaria.negocio.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Venta crear(@RequestBody Venta venta) {
        return ventaService.guardar(venta);
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
