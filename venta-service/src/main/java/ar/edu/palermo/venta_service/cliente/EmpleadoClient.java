package ar.edu.palermo.venta_service.cliente;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ar.edu.palermo.venta_service.dto.SucursalInfoDTO;

@Service
public class EmpleadoClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean existeEmpleado(Integer idEmpleado) {
        try {
            ResponseEntity<Void> response = restTemplate.getForEntity(
                "http://localhost:8084/empleados/" + idEmpleado, Void.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }

    public SucursalInfoDTO obtenerSucursal(Integer idEmpleado) {
        try {
            ResponseEntity<SucursalInfoDTO> response = restTemplate.getForEntity(
                "http://localhost:8084/empleados/" + idEmpleado + "/sucursal", SucursalInfoDTO.class);
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }

       
}
