package ar.edu.palermo.vehiculo_service.controlador;
import ar.edu.palermo.vehiculo_service.dto.VehiculoDTO;
import ar.edu.palermo.vehiculo_service.negocio.IVehiculoService;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/vehiculos")
public class VehiculoControlador {

    private final IVehiculoService vehiculoService;

    @Autowired
    public VehiculoControlador(IVehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @GetMapping
    public List<VehiculoDTO> obtenerTodos() {
        return vehiculoService.obtenerTodos();
    }

    @PostMapping
    public VehiculoDTO crear(@RequestBody VehiculoDTO vehiculo) {
        return vehiculoService.guardar(vehiculo);
    }

    @GetMapping("/{id}")
    public VehiculoDTO obtener(@PathVariable Integer id) {
        return vehiculoService
                .obtenerPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe"));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        vehiculoService.eliminar(id);
    }
}