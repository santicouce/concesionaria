package ar.edu.palermo.empleado_service.negocio;

import ar.edu.palermo.empleado_service.dto.EmpleadoDTO;

import java.util.List;
import java.util.Optional;

public interface IEmpleadoService {

    EmpleadoDTO guardar(EmpleadoDTO empleado);

    List<EmpleadoDTO> obtenerTodos();

    Optional<EmpleadoDTO> obtenerPorId(Integer id);

    void eliminar(Integer id);
}
