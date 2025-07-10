package ar.edu.palermo.empleado_service.controlador;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ar.edu.palermo.empleado_service.cliente.SucursalClient;
import ar.edu.palermo.empleado_service.dominio.Empleado;
import ar.edu.palermo.empleado_service.dto.SucursalInfoDTO;
import ar.edu.palermo.empleado_service.negocio.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/empleados")
public class EmpleadoControlador {

    private final IEmpleadoService empleadoService;
    private final SucursalClient sucursalClient;

    @Autowired
    public EmpleadoControlador(IEmpleadoService empleadoService, SucursalClient sucursalClient) {
        this.empleadoService = empleadoService;
        this.sucursalClient = sucursalClient;
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
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        return empleadoService.obtenerPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empleado no encontrada"));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        empleadoService.eliminar(id);
    }

    @GetMapping("/{id}/sucursal")
    public ResponseEntity<SucursalInfoDTO> obtenerSucursalPorId(@PathVariable Integer id) {
        // 1. Busco el empleado
        Optional<Empleado> empleadoOpt = empleadoService.obtenerPorId(id);
        if (empleadoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Integer sucursalId = empleadoOpt.get().getSucursalId();

        // 2. Busco la sucursal
        Optional<SucursalInfoDTO> sucursalInfoOpt = sucursalClient.obtenerPorId(sucursalId);
        if (sucursalInfoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        SucursalInfoDTO sucursalInfo = sucursalInfoOpt.get();
        return ResponseEntity.ok(sucursalInfo);
    }
    
}
