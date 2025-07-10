package ar.edu.palermo.serviciomecanico_service.negocio.impl;

import ar.edu.palermo.serviciomecanico_service.cliente.VehiculoClient;
import ar.edu.palermo.serviciomecanico_service.cliente.VentaClient;
import ar.edu.palermo.serviciomecanico_service.dominio.ServicioMecanico;
import ar.edu.palermo.serviciomecanico_service.dto.ServicioMecanicoRequest;
import ar.edu.palermo.serviciomecanico_service.dto.VehiculoDTO;
import ar.edu.palermo.serviciomecanico_service.dto.VentaDTO;
import ar.edu.palermo.serviciomecanico_service.exceptions.DatosInvalidosException;
import ar.edu.palermo.serviciomecanico_service.exceptions.VehiculoNotFoundException;
import ar.edu.palermo.serviciomecanico_service.negocio.IServicioMecanicoService;
import ar.edu.palermo.serviciomecanico_service.repositorio.ServicioMecanicoRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioMecanicoService implements IServicioMecanicoService {

    private final ServicioMecanicoRepository servicioRepository;
    private final VehiculoClient vehiculoClient;
    private final VentaClient ventaClient;

    @Autowired
    public ServicioMecanicoService(ServicioMecanicoRepository servicioRepository, VehiculoClient vehiculoClient, VentaClient ventaClient) {
        this.servicioRepository = servicioRepository;
        this.vehiculoClient = vehiculoClient;
        this.ventaClient = ventaClient;
    }

    @Override
    public ServicioMecanico guardar(ServicioMecanicoRequest requestBody) {
        VehiculoDTO vehiculo = vehiculoClient.obtenerVehiculo(requestBody.getVehiculoId());
        if (vehiculo == null) {
            throw new VehiculoNotFoundException("Vehículo no encontrado");
        }
        VentaDTO venta = ventaClient.obtenerVentaPorVehiculo(requestBody.getVehiculoId());
        if (venta == null) {
            throw new DatosInvalidosException("No se encontró una venta asociada al vehículo.");
        }
        if (requestBody.getFecha().isAfter(LocalDate.now())) {
            throw new DatosInvalidosException("La fecha del servicio no puede ser futura.");
        }
        if (requestBody.getKilometraje() < 0) {
            throw new DatosInvalidosException("El kilometraje no puede ser negativo.");
        }
        int aniosDeGarantia = aniosDeGarantiaSegunTipo(vehiculo.getTipo());
        // Si la venta fue hace menos de tantos años, se permite el servicio
        boolean dentroDeGarantia = venta.getFecha().isAfter(LocalDate.now().minusYears(aniosDeGarantia));
        ServicioMecanico servicio = new ServicioMecanico(vehiculo.getId(), requestBody.getFecha(), requestBody.getKilometraje(), dentroDeGarantia);
        return servicioRepository.save(servicio);
    }

    @Override
    public List<ServicioMecanico> obtenerTodos() {
        return servicioRepository.findAll();
    }

    @Override
    public Optional<ServicioMecanico> obtenerPorId(Integer id) {
        return servicioRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        servicioRepository.deleteById(id);
    }

    @Override
    public int aniosDeGarantiaSegunTipo(String tipoVehiculo) {
        switch (tipoVehiculo.toLowerCase()) {
            case "automovil":
                return 2;
            case "camioneta":
                return 3;
            case "moto":
                return 1;
            default:
                throw new DatosInvalidosException("Tipo de vehículo desconocido: " + tipoVehiculo);
        }
    }
}
