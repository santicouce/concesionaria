package ar.edu.palermo.venta_service.negocio;

import ar.edu.palermo.venta_service.dto.VentaDTO;

import java.util.List;
import java.util.Optional;

public interface IVentaService {

    List<VentaDTO> obtenerTodas();

    Optional<VentaDTO> obtenerPorId(Integer id);

    Optional<VentaDTO> obtenerPorVehiculo(Integer idVehiculo);

    void eliminar(Integer id);

    void crearVenta(VentaDTO request);
}
