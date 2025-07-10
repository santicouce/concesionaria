package ar.edu.palermo.sucursal_service.negocio;

import ar.edu.palermo.sucursal_service.dto.SucursalDTO;

import java.util.List;
import java.util.Optional;

public interface ISucursalService {

    SucursalDTO guardar(SucursalDTO sucursal);

    List<SucursalDTO> obtenerTodas();

    Optional<SucursalDTO> obtenerPorId(Integer id);

    void eliminar(Integer id);

    Optional<SucursalDTO> obtenerCentral();
}
