package ar.edu.palermo.concesionaria.negocio;

import ar.edu.palermo.concesionaria.dominio.Empleado;

import java.util.List;
import java.util.Optional;

public interface IEmpleadoService {

    Empleado guardar(Empleado empleado);

    List<Empleado> obtenerTodos();

    Optional<Empleado> obtenerPorId(Integer id);

    void eliminar(Integer id);
}
