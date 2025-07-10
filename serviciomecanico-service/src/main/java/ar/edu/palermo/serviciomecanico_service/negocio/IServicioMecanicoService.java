package ar.edu.palermo.serviciomecanico_service.negocio;

import ar.edu.palermo.serviciomecanico_service.dominio.ServicioMecanico;
import ar.edu.palermo.serviciomecanico_service.dto.ServicioMecanicoRequest;

import java.util.List;
import java.util.Optional;

public interface IServicioMecanicoService {

    ServicioMecanico guardar(ServicioMecanicoRequest requestBody);

    List<ServicioMecanico> obtenerTodos();

    Optional<ServicioMecanico> obtenerPorId(Integer id);

    void eliminar(Integer id);

    int aniosDeGarantiaSegunTipo(String tipoVehiculo);
}
