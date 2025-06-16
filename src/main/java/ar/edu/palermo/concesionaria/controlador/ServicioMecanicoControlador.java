package ar.edu.palermo.concesionaria.controlador;

import ar.edu.palermo.concesionaria.dominio.ServicioMecanico;
import ar.edu.palermo.concesionaria.negocio.IServicioMecanicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servicio-mecanico")
public class ServicioMecanicoControlador {

    private final IServicioMecanicoService servicioService;

    @Autowired
    public ServicioMecanicoControlador(IServicioMecanicoService servicioService) {
        this.servicioService = servicioService;
    }

    @GetMapping
    public List<ServicioMecanico> obtenerTodos() {
        return servicioService.obtenerTodos();
    }

    @PostMapping
    public ServicioMecanico crear(@RequestBody ServicioMecanico servicio) {
        return servicioService.guardar(servicio);
    }

    @GetMapping("/{id}")
    public Optional<ServicioMecanico> obtenerPorId(@PathVariable Integer id) {
        return servicioService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        servicioService.eliminar(id);
    }
}
