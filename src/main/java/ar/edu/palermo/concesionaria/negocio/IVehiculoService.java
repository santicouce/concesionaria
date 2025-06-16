package ar.edu.palermo.concesionaria.negocio;

import ar.edu.palermo.concesionaria.dominio.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface IVehiculoService {

    Vehiculo guardar(Vehiculo vehiculo);

    List<Vehiculo> obtenerTodos();

    Optional<Vehiculo> obtenerPorId(Integer id);

    void eliminar(Integer id);
}
