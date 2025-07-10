package ar.edu.palermo.cliente_service.controlador;

import ar.edu.palermo.cliente_service.dto.ClienteDTO;
import ar.edu.palermo.cliente_service.negocio.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ClienteDTO crear(@RequestBody ClienteDTO cliente) {
        return clienteService.guardar(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        return clienteService.obtenerPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrada"));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        clienteService.eliminar(id);
    }
}
