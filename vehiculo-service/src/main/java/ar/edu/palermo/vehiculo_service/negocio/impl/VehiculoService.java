package ar.edu.palermo.vehiculo_service.negocio.impl;

import ar.edu.palermo.vehiculo_service.dominio.Vehiculo;
import ar.edu.palermo.vehiculo_service.negocio.IVehiculoService;
import ar.edu.palermo.vehiculo_service.repositorio.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoService implements IVehiculoService {

    private final VehiculoRepository vehiculoRepository;

    @Autowired
    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public Vehiculo guardar(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public List<Vehiculo> obtenerTodos() {
        return vehiculoRepository.findAll();
    }

    @Override
    public Optional<Vehiculo> obtenerPorId(Integer id) {
        return vehiculoRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        vehiculoRepository.deleteById(id);
    }
}
