package ar.edu.palermo.sucursal_service.negocio;

import ar.edu.palermo.sucursal_service.dominio.Sucursal;

import java.util.List;
import java.util.Optional;

public interface ISucursalService {

    Sucursal guardar(Sucursal sucursal);

    List<Sucursal> obtenerTodas();

    Optional<Sucursal> obtenerPorId(Integer id);

    void eliminar(Integer id);

    Optional<Sucursal> obtenerCentral();
}
