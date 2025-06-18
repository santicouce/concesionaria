package ar.edu.palermo.concesionaria.controlador;

import ar.edu.palermo.concesionaria.dominio.Sucursal;
import ar.edu.palermo.concesionaria.dominio.Vehiculo;
import ar.edu.palermo.concesionaria.negocio.IServicioEntrega;
import ar.edu.palermo.concesionaria.repositorio.SucursalRepository;
import ar.edu.palermo.concesionaria.repositorio.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/entregas")
public class EntregaControlador {
    // Expongo estos endpoints con fines demostrativos, y para testeo, ya que no es explicitamente requerido
    // un controlador para que el servicio de ventas pueda calcular el tiempo de entrega.
    private final IServicioEntrega servicioEntrega;
    private final SucursalRepository sucursalRepository;
    private final VehiculoRepository vehiculoRepository;

    @Autowired
    public EntregaControlador(IServicioEntrega servicioEntrega,
                              SucursalRepository sucursalRepository,
                              VehiculoRepository vehiculoRepository) {
        this.servicioEntrega = servicioEntrega;
        this.sucursalRepository = sucursalRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    @GetMapping("/tiempo")
    public String calcularTiempo(@RequestParam Integer idSucursal, @RequestParam Integer idVehiculo) {
        Optional<Sucursal> sucursal = sucursalRepository.findById(idSucursal);
        Optional<Vehiculo> vehiculo = vehiculoRepository.findById(idVehiculo);

        if (sucursal.isEmpty() || vehiculo.isEmpty()) {
            return "Sucursal o vehículo no encontrados";
        }

        Integer dias = servicioEntrega.calcularTiempoEntrega(sucursal.get(), vehiculo.get());
        return "Tiempo estimado de entrega: " + dias + " días";
    }
}
