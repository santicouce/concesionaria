package ar.edu.palermo.sucursal_service.negocio.impl;

import ar.edu.palermo.sucursal_service.dominio.Sucursal;
import ar.edu.palermo.sucursal_service.dto.SucursalDTO;
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
    public SucursalDTO guardar(SucursalDTO sucursal) {
        validarDatosSucursal(sucursal);
        Sucursal nuevaSucursal = new Sucursal(sucursal.getNombre(),
                sucursal.getDireccion(),
                sucursal.getLocalidad(),
                sucursal.getProvincia(),
                sucursal.getPais(),
                sucursal.getFechaApertura(),
                sucursal.getDiasEntregaCliente(),
                sucursal.getDiasEntregaDesdeCentral(),
                sucursal.getEsCentral());
        sucursalRepository.save(nuevaSucursal);
        sucursal.setId(nuevaSucursal.getId());
        return sucursal;
    }

    @Override
    public List<SucursalDTO> obtenerTodas() {
        return sucursalRepository.findAll()
                .stream()
                .map(sucursal -> {
                    SucursalDTO dto = new SucursalDTO();
                    dto.setId(sucursal.getId());
                    dto.setNombre(sucursal.getNombre());
                    dto.setDireccion(sucursal.getDireccion());
                    dto.setLocalidad(sucursal.getLocalidad());
                    dto.setProvincia(sucursal.getProvincia());
                    dto.setPais(sucursal.getPais());
                    dto.setDiasEntregaCliente(sucursal.getDiasEntregaCliente());
                    dto.setDiasEntregaDesdeCentral(sucursal.getDiasEntregaDesdeCentral());
                    dto.setEsCentral(sucursal.getEsCentral());
                    return dto;
                })
                .toList();
    }

    @Override
    public Optional<SucursalDTO> obtenerPorId(Integer id) {
        return sucursalRepository.findById(id)
                .map(sucursal -> {
                    SucursalDTO dto = new SucursalDTO();
                    dto.setId(sucursal.getId());
                    dto.setNombre(sucursal.getNombre());
                    dto.setDireccion(sucursal.getDireccion());
                    dto.setLocalidad(sucursal.getLocalidad());
                    dto.setProvincia(sucursal.getProvincia());
                    dto.setPais(sucursal.getPais());
                    dto.setDiasEntregaCliente(sucursal.getDiasEntregaCliente());
                    dto.setDiasEntregaDesdeCentral(sucursal.getDiasEntregaDesdeCentral());
                    dto.setEsCentral(sucursal.getEsCentral());
                    return dto;
                });
    }

    @Override
    public void eliminar(Integer id) {
        sucursalRepository.deleteById(id);
    }

    // Validaciones
    private void validarDatosSucursal(SucursalDTO sucursal) {
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
    public Optional<SucursalDTO> obtenerCentral() {
        return sucursalRepository.findByEsCentralTrue()
                .map(nullable -> {
                    SucursalDTO sucursalDTO = new SucursalDTO();
                    sucursalDTO.setId(nullable.getId());
                    sucursalDTO.setNombre(nullable.getNombre());
                    sucursalDTO.setDireccion(nullable.getDireccion());
                    sucursalDTO.setLocalidad(nullable.getLocalidad());
                    sucursalDTO.setProvincia(nullable.getProvincia());
                    sucursalDTO.setPais(nullable.getPais());
                    sucursalDTO.setDiasEntregaCliente(nullable.getDiasEntregaCliente());
                    sucursalDTO.setDiasEntregaDesdeCentral(nullable.getDiasEntregaDesdeCentral());
                    return sucursalDTO;
                });
    }

}
