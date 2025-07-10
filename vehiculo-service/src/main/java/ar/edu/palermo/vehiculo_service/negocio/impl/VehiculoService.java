package ar.edu.palermo.vehiculo_service.negocio.impl;

import ar.edu.palermo.vehiculo_service.dominio.Vehiculo;
import ar.edu.palermo.vehiculo_service.dto.VehiculoDTO;
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
    public VehiculoDTO guardar(VehiculoDTO vehiculo) {
        Vehiculo vehiculoEntity = new Vehiculo(vehiculo.getNumeroChasis(),
                                               vehiculo.getMarca(),
                                               vehiculo.getModelo(),
                                               vehiculo.getAnio(),
                                               vehiculo.getTipo());
        vehiculoRepository.save(vehiculoEntity);
        vehiculo.setId(vehiculoEntity.getId());
        return vehiculo;
    }

    @Override
    public List<VehiculoDTO> obtenerTodos() {
        return vehiculoRepository.findAll()
                .stream()
                .map(vehiculo -> new VehiculoDTO(vehiculo.getId(),
                                                 vehiculo.getNumeroChasis(),
                                                 vehiculo.getMarca(),
                                                 vehiculo.getModelo(),
                                                 vehiculo.getAnio(),
                                                 vehiculo.getTipo()))
                .toList();
    }

    @Override
    public Optional<VehiculoDTO> obtenerPorId(Integer id) {
        return vehiculoRepository.findById(id)
                .map(vehiculo -> new VehiculoDTO(vehiculo.getId(),
                                                 vehiculo.getNumeroChasis(),
                                                 vehiculo.getMarca(),
                                                 vehiculo.getModelo(),
                                                 vehiculo.getAnio(),
                                                 vehiculo.getTipo()));
    }

    @Override
    public void eliminar(Integer id) {
        vehiculoRepository.deleteById(id);
    }
}
