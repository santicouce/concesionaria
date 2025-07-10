package ar.edu.palermo.venta_service.cliente;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VehiculoClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean existeVehiculo(Integer idVehiculo) {
        try {
            ResponseEntity<Void> response = restTemplate.getForEntity(
                "http://localhost:8083/vehiculos/" + idVehiculo, Void.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }
}
