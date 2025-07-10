package ar.edu.palermo.serviciomecanico_service.controlador;

import ar.edu.palermo.serviciomecanico_service.dto.ServicioMecanicoDTO;
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
    public List<ServicioMecanicoDTO> obtenerTodos() {
        return servicioService.obtenerTodos();
    }

    @PostMapping
    public ServicioMecanicoDTO crear(@RequestBody ServicioMecanicoDTO request) {
        return servicioService.guardar(request);
        
    }

    @GetMapping("/{id}")
    public Optional<ServicioMecanicoDTO> obtenerPorId(@PathVariable Integer id) {
        return servicioService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        servicioService.eliminar(id);
    }
}
