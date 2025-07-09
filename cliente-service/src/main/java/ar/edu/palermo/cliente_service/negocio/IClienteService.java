package ar.edu.palermo.cliente_service.negocio;

import ar.edu.palermo.cliente_service.dominio.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {

    Cliente guardar(Cliente cliente);

    List<Cliente> obtenerTodos();

    Optional<Cliente> obtenerPorId(Integer id);

    void eliminar(Integer id);
}
