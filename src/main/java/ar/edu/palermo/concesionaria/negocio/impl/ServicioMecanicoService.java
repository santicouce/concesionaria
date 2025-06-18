package ar.edu.palermo.concesionaria.negocio.impl;

import ar.edu.palermo.concesionaria.dominio.Cliente;
import ar.edu.palermo.concesionaria.dominio.ServicioMecanico;
import ar.edu.palermo.concesionaria.dominio.Vehiculo;
import ar.edu.palermo.concesionaria.dominio.Venta;
import ar.edu.palermo.concesionaria.dto.ServicioMecanicoRequest;
import ar.edu.palermo.concesionaria.exceptions.DatosInvalidosException;
import ar.edu.palermo.concesionaria.exceptions.NegocioException;
import ar.edu.palermo.concesionaria.negocio.IServicioMecanicoService;
import ar.edu.palermo.concesionaria.repositorio.ClienteRepository;
import ar.edu.palermo.concesionaria.repositorio.ServicioMecanicoRepository;
import ar.edu.palermo.concesionaria.repositorio.VehiculoRepository;
import ar.edu.palermo.concesionaria.repositorio.VentaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioMecanicoService implements IServicioMecanicoService {

    private final ServicioMecanicoRepository servicioRepository;
    private final VehiculoRepository vehiculoRepository;
    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public ServicioMecanicoService(ServicioMecanicoRepository servicioRepository, VehiculoRepository vehiculoRepository, VentaRepository ventaRepository, ClienteRepository clienteRepository) {
        this.servicioRepository = servicioRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ServicioMecanico guardar(ServicioMecanicoRequest requestBody) {
        Vehiculo vehiculo = vehiculoRepository.findById(requestBody.getVehiculoId())
                .orElseThrow(() -> new DatosInvalidosException("Vehiculo no encontrado"));
        Venta venta = ventaRepository.findByVehiculoId(vehiculo.getId())
                .orElseThrow(() -> new NegocioException("No se encontró una venta asociada al vehículo"));
        Cliente cliente = clienteRepository.findById(requestBody.getClienteId())
                .orElseThrow(() -> new DatosInvalidosException("Cliente no encontrado"));
        if (requestBody.getFecha().isAfter(LocalDate.now())) {
            throw new DatosInvalidosException("La fecha del servicio no puede ser futura.");
        }
        if (requestBody.getKilometraje() < 0) {
            throw new DatosInvalidosException("El kilometraje no puede ser negativo.");
        }
        int aniosDeGarantia = aniosDeGarantiaSegunTipo(vehiculo.getTipo());
        // Si la venta fue hace menos de tantos años, se permite el servicio
        boolean dentroDeGarantia = venta.getFecha().isAfter(LocalDate.now().minusYears(aniosDeGarantia));
        ServicioMecanico servicio = new ServicioMecanico(cliente, vehiculo, requestBody.getFecha(), requestBody.getKilometraje(), dentroDeGarantia);
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
