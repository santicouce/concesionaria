package ar.edu.palermo.empleado_service.negocio.impl;

import ar.edu.palermo.empleado_service.cliente.SucursalClient;
import ar.edu.palermo.empleado_service.dominio.Empleado;
import ar.edu.palermo.empleado_service.dto.EmpleadoDTO;
import ar.edu.palermo.empleado_service.exceptions.SucursalNotFoundException;
import ar.edu.palermo.empleado_service.negocio.IEmpleadoService;
import ar.edu.palermo.empleado_service.repositorio.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService implements IEmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final SucursalClient sucursalClient;

    @Autowired
    public EmpleadoService(EmpleadoRepository empleadoRepository, SucursalClient sucursalClient) {
        this.empleadoRepository = empleadoRepository;
        this.sucursalClient = sucursalClient;
    }

    @Override
    public EmpleadoDTO guardar(EmpleadoDTO empleado) {
        if (!sucursalClient.existeSucursal(empleado.getSucursalId())) {
            throw new SucursalNotFoundException("La sucursal no existe");
        }
        Empleado nuevoEmpleado = new Empleado(empleado.getNombre(),
                empleado.getApellido(),
                empleado.getLegajo(),
                empleado.getRol(),
                empleado.getSucursalId());
        empleadoRepository.save(nuevoEmpleado);
        empleado.setId(nuevoEmpleado.getId());
        return empleado;
    }

    @Override
    public List<EmpleadoDTO> obtenerTodos() {
        return empleadoRepository.findAll()
                .stream()
                .map(empleado -> {
                    EmpleadoDTO dto = new EmpleadoDTO();
                    dto.setId(empleado.getId());
                    dto.setNombre(empleado.getNombre());
                    dto.setApellido(empleado.getApellido());
                    dto.setLegajo(empleado.getLegajo());
                    dto.setRol(empleado.getRol());
                    dto.setSucursalId(empleado.getSucursalId());
                    return dto;
                })
                .toList();
    }

    @Override
    public Optional<EmpleadoDTO> obtenerPorId(Integer id) {
        return empleadoRepository.findById(id)
                .map(empleado -> {
                    EmpleadoDTO dto = new EmpleadoDTO();
                    dto.setId(empleado.getId());
                    dto.setNombre(empleado.getNombre());
                    dto.setApellido(empleado.getApellido());
                    dto.setLegajo(empleado.getLegajo());
                    dto.setRol(empleado.getRol());
                    dto.setSucursalId(empleado.getSucursalId());
                    return dto;
                });
    }

    @Override
    public void eliminar(Integer id) {
        empleadoRepository.deleteById(id);
    }
}
