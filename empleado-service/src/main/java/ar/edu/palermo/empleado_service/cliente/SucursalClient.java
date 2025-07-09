package ar.edu.palermo.empleado_service.cliente;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Service
public class SucursalClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean existeSucursal(Integer idSucursal) {
        try {
            ResponseEntity<Void> response = restTemplate.getForEntity(
                "http://localhost:8086/sucursales/" + idSucursal, Void.class);
            System.out.print("Response: " + response.getStatusCode());
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }
}
