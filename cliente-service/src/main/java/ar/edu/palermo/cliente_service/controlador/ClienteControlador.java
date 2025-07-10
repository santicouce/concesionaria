package ar.edu.palermo.cliente_service.controlador;

import ar.edu.palermo.cliente_service.dto.ClienteDTO;
import ar.edu.palermo.cliente_service.exceptions.ObjetoRelacionadoNoEncontrado;
import ar.edu.palermo.cliente_service.negocio.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteControlador {

    private final IClienteService clienteService;

    @Autowired
    public ClienteControlador(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteDTO> obtenerTodos() {
        return clienteService.obtenerTodos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO crear(@RequestBody ClienteDTO cliente) {
        return clienteService.guardar(cliente);
    }

    @GetMapping("/{id}")
    public ClienteDTO obtenerPorId(@PathVariable Integer id) {
        return clienteService.obtenerPorId(id)
            .orElseThrow(() -> new ObjetoRelacionadoNoEncontrado(
                "Cliente no encontrado (id=" + id + ")"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        clienteService.eliminar(id);
    }
}
