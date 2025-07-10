package ar.edu.palermo.cliente_service.negocio;
import ar.edu.palermo.cliente_service.dto.ClienteDTO;

import java.util.List;
import java.util.Optional;

public interface IClienteService {

    ClienteDTO guardar(ClienteDTO cliente);

    List<ClienteDTO> obtenerTodos();

    Optional<ClienteDTO> obtenerPorId(Integer id);

    void eliminar(Integer id);
}
