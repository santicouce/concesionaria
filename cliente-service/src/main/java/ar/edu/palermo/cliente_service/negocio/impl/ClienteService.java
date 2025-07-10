package ar.edu.palermo.cliente_service.negocio.impl;

import ar.edu.palermo.cliente_service.dominio.Cliente;
import ar.edu.palermo.cliente_service.dto.ClienteDTO;
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
    public ClienteDTO guardar(ClienteDTO cliente) {
        Cliente clienteEntity = new Cliente();
        clienteEntity.setNombre(cliente.getNombre());
        clienteEntity.setApellido(cliente.getApellido());
        clienteEntity.setTelefono(cliente.getTelefono());
        clienteEntity.setDni(cliente.getDni());
        clienteRepository.save(clienteEntity);
        cliente.setId(clienteEntity.getId());
        return cliente;
    }

    @Override
    public List<ClienteDTO> obtenerTodos() {
        return clienteRepository.findAll().stream()
            .map(c -> new ClienteDTO(
                c.getId(), c.getNombre(), c.getApellido(), c.getDni(), c.getTelefono()
            ))
            .toList();
    }

    @Override
    public Optional<ClienteDTO> obtenerPorId(Integer id) {
        return clienteRepository.findById(id)
            .map(c -> new ClienteDTO(
                c.getId(),
                c.getNombre(),
                c.getApellido(),
                c.getDni(),
                c.getTelefono()
            ));
    }

    @Override
    public void eliminar(Integer id) {
        clienteRepository.deleteById(id);
    }
}
