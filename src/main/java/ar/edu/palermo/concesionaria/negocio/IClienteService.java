package ar.edu.palermo.concesionaria.negocio;

import ar.edu.palermo.concesionaria.dominio.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {

    Cliente guardar(Cliente cliente);

    List<Cliente> obtenerTodos();

    Optional<Cliente> obtenerPorId(Integer id);

    void eliminar(Integer id);
}
