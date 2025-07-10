package ar.edu.palermo.serviciomecanico_service.controlador;

import ar.edu.palermo.serviciomecanico_service.dominio.ServicioMecanico;
import ar.edu.palermo.serviciomecanico_service.dto.ServicioMecanicoRequest;
import ar.edu.palermo.serviciomecanico_service.negocio.IServicioMecanicoService;
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
    public ServicioMecanico crear(@RequestBody ServicioMecanicoRequest request) {
        return servicioService.guardar(request);
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
