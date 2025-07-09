package ar.edu.palermo.cliente_service.negocio.impl;

import ar.edu.palermo.cliente_service.dominio.Cliente;
import ar.edu.palermo.cliente_service.negocio.IClienteService;
import ar.edu.palermo.cliente_service.repositorio.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> obtenerPorId(Integer id) {
        return clienteRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        clienteRepository.deleteById(id);
    }
}
