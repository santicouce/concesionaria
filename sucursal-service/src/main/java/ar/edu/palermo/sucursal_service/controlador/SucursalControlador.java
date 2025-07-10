package ar.edu.palermo.sucursal_service.controlador;
import org.springframework.http.HttpStatus;
import ar.edu.palermo.sucursal_service.dto.SucursalDTO;
import ar.edu.palermo.sucursal_service.negocio.ISucursalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sucursales")
public class SucursalControlador {

    private final ISucursalService sucursalService;

    @Autowired
    public SucursalControlador(ISucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    @GetMapping
    public List<SucursalDTO> obtenerTodas() {
        return sucursalService.obtenerTodas();
    }

    @PostMapping
    public SucursalDTO crear(@RequestBody SucursalDTO sucursal) {
        return sucursalService.guardar(sucursal);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        return sucursalService.obtenerPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sucursal no encontrada"));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        sucursalService.eliminar(id);
    }

    @GetMapping("/central")
    public ResponseEntity<Integer> obtenerIdSucursalCentral() {
        Optional<SucursalDTO> sucursalCentral = sucursalService.obtenerCentral();
        return sucursalCentral
            .map(sucursal -> ResponseEntity.ok(sucursal.getId()))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
