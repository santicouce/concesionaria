package ar.edu.palermo.serviciomecanico_service.cliente;

import ar.edu.palermo.serviciomecanico_service.dto.VentaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VentaClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public VentaDTO obtenerVentaPorVehiculo(Integer vehiculoId) {
        try {
            ResponseEntity<VentaDTO> response = restTemplate.getForEntity(
                "http://localhost:8085/ventas/vehiculo/" + vehiculoId, VentaDTO.class);
            return response.getBody();
        } catch (Exception e) {
            System.err.println("Error al obtener la venta: " + e.getMessage());
            return null;
        }
    }
}
