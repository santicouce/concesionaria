package ar.edu.palermo.concesionaria.controlador;

import ar.edu.palermo.concesionaria.dominio.Sucursal;
import ar.edu.palermo.concesionaria.negocio.ISucursalService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Sucursal> obtenerTodas() {
        return sucursalService.obtenerTodas();
    }

    @PostMapping
    public Sucursal crear(@RequestBody Sucursal sucursal) {
        return sucursalService.guardar(sucursal);
    }

    @GetMapping("/{id}")
    public Optional<Sucursal> obtenerPorId(@PathVariable Integer id) {
        return sucursalService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        sucursalService.eliminar(id);
    }
}
