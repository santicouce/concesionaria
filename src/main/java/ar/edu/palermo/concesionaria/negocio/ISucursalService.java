package ar.edu.palermo.concesionaria.negocio;

import ar.edu.palermo.concesionaria.dominio.Sucursal;

import java.util.List;
import java.util.Optional;

public interface ISucursalService {

    Sucursal guardar(Sucursal sucursal);

    List<Sucursal> obtenerTodas();

    Optional<Sucursal> obtenerPorId(Integer id);

    void eliminar(Integer id);
}
