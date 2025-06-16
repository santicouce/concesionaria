package ar.edu.palermo.concesionaria.controlador;

import ar.edu.palermo.concesionaria.dominio.Empleado;
import ar.edu.palermo.concesionaria.negocio.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleados")
public class EmpleadoControlador {

    private final IEmpleadoService empleadoService;

    @Autowired
    public EmpleadoControlador(IEmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public List<Empleado> obtenerTodos() {
        return empleadoService.obtenerTodos();
    }

    @PostMapping
    public Empleado crear(@RequestBody Empleado empleado) {
        return empleadoService.guardar(empleado);
    }

    @GetMapping("/{id}")
    public Optional<Empleado> obtenerPorId(@PathVariable Integer id) {
        return empleadoService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        empleadoService.eliminar(id);
    }
}
