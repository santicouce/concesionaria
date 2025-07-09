package ar.edu.palermo.vehiculo_service.controlador;

import ar.edu.palermo.vehiculo_service.dominio.Vehiculo;
import ar.edu.palermo.vehiculo_service.negocio.IVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoControlador {

    private final IVehiculoService vehiculoService;

    @Autowired
    public VehiculoControlador(IVehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @GetMapping
    public List<Vehiculo> obtenerTodos() {
        return vehiculoService.obtenerTodos();
    }

    @PostMapping
    public Vehiculo crear(@RequestBody Vehiculo vehiculo) {
        return vehiculoService.guardar(vehiculo);
    }

    @GetMapping("/{id}")
    public Optional<Vehiculo> obtenerPorId(@PathVariable Integer id) {
        return vehiculoService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        vehiculoService.eliminar(id);
    }
}
