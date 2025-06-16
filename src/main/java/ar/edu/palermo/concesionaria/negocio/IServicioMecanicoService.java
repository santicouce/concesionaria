package ar.edu.palermo.concesionaria.negocio;

import ar.edu.palermo.concesionaria.dominio.ServicioMecanico;

import java.util.List;
import java.util.Optional;

public interface IServicioMecanicoService {

    ServicioMecanico guardar(ServicioMecanico servicio);

    List<ServicioMecanico> obtenerTodos();

    Optional<ServicioMecanico> obtenerPorId(Integer id);

    void eliminar(Integer id);
}
