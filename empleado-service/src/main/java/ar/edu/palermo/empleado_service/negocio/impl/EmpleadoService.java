package ar.edu.palermo.empleado_service.negocio.impl;

import ar.edu.palermo.empleado_service.cliente.SucursalClient;
import ar.edu.palermo.empleado_service.dominio.Empleado;
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
    public Empleado guardar(Empleado empleado) {
        if (!sucursalClient.existeSucursal(empleado.getSucursalId())) {
            throw new SucursalNotFoundException("La sucursal no existe");
        }
        return empleadoRepository.save(empleado);
    }

    @Override
    public List<Empleado> obtenerTodos() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> obtenerPorId(Integer id) {
        return empleadoRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        empleadoRepository.deleteById(id);
    }
}
