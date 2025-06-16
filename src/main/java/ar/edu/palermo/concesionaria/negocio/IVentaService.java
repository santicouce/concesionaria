package ar.edu.palermo.concesionaria.negocio;

import ar.edu.palermo.concesionaria.dominio.Venta;

import java.util.List;
import java.util.Optional;

public interface IVentaService {

    Venta guardar(Venta venta);

    List<Venta> obtenerTodas();

    Optional<Venta> obtenerPorId(Integer id);

    void eliminar(Integer id);
}
