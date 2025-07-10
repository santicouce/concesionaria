package ar.edu.palermo.empleado_service.controlador;

import ar.edu.palermo.empleado_service.cliente.SucursalClient;
import ar.edu.palermo.empleado_service.dto.EmpleadoDTO;
import ar.edu.palermo.empleado_service.dto.SucursalInfoDTO;
import ar.edu.palermo.empleado_service.exceptions.ObjetoRelacionadoNoEncontrado;
import ar.edu.palermo.empleado_service.negocio.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoControlador {

    private final IEmpleadoService empleadoService;
    private final SucursalClient sucursalClient;

    @Autowired
    public EmpleadoControlador(IEmpleadoService empleadoService,
                               SucursalClient sucursalClient) {
        this.empleadoService = empleadoService;
        this.sucursalClient = sucursalClient;
    }

    @GetMapping
    public List<EmpleadoDTO> obtenerTodos() {
        return empleadoService.obtenerTodos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmpleadoDTO crear(@RequestBody EmpleadoDTO empleado) {
        return empleadoService.guardar(empleado);
    }

    @GetMapping("/{id}")
    public EmpleadoDTO obtenerPorId(@PathVariable Integer id) {
        return empleadoService.obtenerPorId(id)
            .orElseThrow(() -> new ObjetoRelacionadoNoEncontrado(
                "Empleado no encontrado (id=" + id + ")"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        empleadoService.eliminar(id);
    }

    @GetMapping("/{id}/sucursal")
    public SucursalInfoDTO obtenerSucursalPorId(@PathVariable Integer id) {
        // 1) Validar existencia de empleado
        EmpleadoDTO empleado = empleadoService.obtenerPorId(id)
            .orElseThrow(() -> new ObjetoRelacionadoNoEncontrado(
                "Empleado no encontrado (id=" + id + ")"));

        // 2) Pedir info de sucursal al cliente
        Integer sucursalId = empleado.getSucursalId();
        return sucursalClient.obtenerPorId(sucursalId)
            .orElseThrow(() -> new ObjetoRelacionadoNoEncontrado(
                "Sucursal no encontrada (id=" + sucursalId + ")"));
    }
}
