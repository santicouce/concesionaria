package ar.edu.palermo.concesionaria.negocio.impl;

import ar.edu.palermo.concesionaria.dominio.ServicioMecanico;
import ar.edu.palermo.concesionaria.exceptions.DatosInvalidosException;
import ar.edu.palermo.concesionaria.negocio.IServicioMecanicoService;
import ar.edu.palermo.concesionaria.repositorio.ServicioMecanicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioMecanicoService implements IServicioMecanicoService {

    private final ServicioMecanicoRepository servicioRepository;

    @Autowired
    public ServicioMecanicoService(ServicioMecanicoRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @Override
    public ServicioMecanico guardar(ServicioMecanico servicio) {
        if (servicio.getFecha().isAfter(LocalDate.now())) {
            throw new DatosInvalidosException("La fecha del servicio no puede ser futura.");
        }
        if (servicio.getKilometraje() < 0) {
            throw new DatosInvalidosException("El kilometraje no puede ser negativo.");
        }
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
}
