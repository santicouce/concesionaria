package ar.edu.palermo.serviciomecanico_service.cliente;

import ar.edu.palermo.serviciomecanico_service.dto.VehiculoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VehiculoClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public VehiculoDTO obtenerVehiculo(Integer idVehiculo) {
        try {
            ResponseEntity<VehiculoDTO> response = restTemplate.getForEntity(
                "http://localhost:8083/vehiculos/" + idVehiculo, VehiculoDTO.class);
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
