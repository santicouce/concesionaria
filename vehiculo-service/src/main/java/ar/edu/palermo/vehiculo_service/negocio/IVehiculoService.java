package ar.edu.palermo.vehiculo_service.negocio;

import ar.edu.palermo.vehiculo_service.dto.VehiculoDTO;

import java.util.List;
import java.util.Optional;

public interface IVehiculoService {

    VehiculoDTO guardar(VehiculoDTO vehiculo);

    List<VehiculoDTO> obtenerTodos();

    Optional<VehiculoDTO> obtenerPorId(Integer id);

    void eliminar(Integer id);
}
