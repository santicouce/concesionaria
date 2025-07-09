package ar.edu.palermo.stock_service.cliente;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SucursalClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean existeSucursal(Integer sucursalId) {
        try {
            ResponseEntity<Void> response = restTemplate.getForEntity(
                "http://localhost:8086/sucursales/" + sucursalId,
                Void.class
            );
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }
}
