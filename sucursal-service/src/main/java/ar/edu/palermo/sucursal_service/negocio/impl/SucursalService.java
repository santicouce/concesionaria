package ar.edu.palermo.sucursal_service.negocio.impl;

import ar.edu.palermo.sucursal_service.dominio.Sucursal;
import ar.edu.palermo.sucursal_service.exceptions.DatosInvalidosException;
import ar.edu.palermo.sucursal_service.negocio.ISucursalService;
import ar.edu.palermo.sucursal_service.repositorio.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SucursalService implements ISucursalService {

    private final SucursalRepository sucursalRepository;

    @Autowired
    public SucursalService(SucursalRepository sucursalRepository) {
        this.sucursalRepository = sucursalRepository;
    }

    @Override
    public Sucursal guardar(Sucursal sucursal) {
        validarDatosSucursal(sucursal);
        return sucursalRepository.save(sucursal);
    }

    @Override
    public List<Sucursal> obtenerTodas() {
        return sucursalRepository.findAll();
    }

    @Override
    public Optional<Sucursal> obtenerPorId(Integer id) {
        return sucursalRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        sucursalRepository.deleteById(id);
    }

    // Validaciones
    private void validarDatosSucursal(Sucursal sucursal) {
        if (esInvalido(sucursal.getNombre())
            || esInvalido(sucursal.getDireccion())
            || esInvalido(sucursal.getLocalidad())
            || esInvalido(sucursal.getProvincia())
            || esInvalido(sucursal.getPais())
            || sucursal.getDiasEntregaCliente() == null
            || sucursal.getDiasEntregaDesdeCentral() == null) {
            
            throw new DatosInvalidosException("Faltan datos obligatorios para crear la sucursal");
        }
        // Validar que solo haya una sucursal central
        if (Boolean.TRUE.equals(sucursal.getEsCentral())) {
            Optional<Sucursal> existente = sucursalRepository.findByEsCentralTrue();
            if (existente.isPresent()) {
                throw new DatosInvalidosException("Ya existe una sucursal central registrada");
            }
        }
    }

    private boolean esInvalido(String campo) {
        return campo == null || campo.isBlank();
    }

    @Override
    public Optional<Sucursal> obtenerCentral() {
        return sucursalRepository.findByEsCentralTrue();
    }

}
