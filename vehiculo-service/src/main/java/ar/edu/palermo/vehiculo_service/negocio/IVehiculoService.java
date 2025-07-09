package ar.edu.palermo.vehiculo_service.negocio;

import ar.edu.palermo.vehiculo_service.dominio.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface IVehiculoService {

    Vehiculo guardar(Vehiculo vehiculo);

    List<Vehiculo> obtenerTodos();

    Optional<Vehiculo> obtenerPorId(Integer id);

    void eliminar(Integer id);
}
