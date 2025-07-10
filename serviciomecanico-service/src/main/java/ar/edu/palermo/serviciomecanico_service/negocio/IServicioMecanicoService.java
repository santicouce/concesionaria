package ar.edu.palermo.serviciomecanico_service.negocio;

import ar.edu.palermo.serviciomecanico_service.dto.ServicioMecanicoDTO;

import java.util.List;
import java.util.Optional;

public interface IServicioMecanicoService {

    ServicioMecanicoDTO guardar(ServicioMecanicoDTO requestBody);

    List<ServicioMecanicoDTO> obtenerTodos();

    Optional<ServicioMecanicoDTO> obtenerPorId(Integer id);

    void eliminar(Integer id);

    int aniosDeGarantiaSegunTipo(String tipoVehiculo);
}
