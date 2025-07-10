package ar.edu.palermo.venta_service.cliente;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClienteClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean existeCliente(Integer idCliente) {
        try {
            ResponseEntity<Void> response = restTemplate.getForEntity(
                "http://localhost:8081/clientes/" + idCliente, Void.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }
}
