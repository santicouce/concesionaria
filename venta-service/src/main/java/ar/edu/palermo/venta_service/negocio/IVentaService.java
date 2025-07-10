package ar.edu.palermo.venta_service.negocio;

import ar.edu.palermo.venta_service.dominio.Venta;
import ar.edu.palermo.venta_service.dto.VentaRequest;

import java.util.List;
import java.util.Optional;

public interface IVentaService {

    List<Venta> obtenerTodas();

    Optional<Venta> obtenerPorId(Integer id);

    void eliminar(Integer id);

    void crearVenta(VentaRequest request);
}
