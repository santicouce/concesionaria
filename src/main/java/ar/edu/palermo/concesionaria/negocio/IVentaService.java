package ar.edu.palermo.concesionaria.negocio;

import ar.edu.palermo.concesionaria.dominio.Venta;
import ar.edu.palermo.concesionaria.dto.VentaRequest;

import java.util.List;
import java.util.Optional;

public interface IVentaService {

    List<Venta> obtenerTodas();

    Optional<Venta> obtenerPorId(Integer id);

    void eliminar(Integer id);

    void crearVenta(VentaRequest request);
}
