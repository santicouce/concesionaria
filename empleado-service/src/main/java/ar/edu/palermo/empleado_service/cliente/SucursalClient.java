package ar.edu.palermo.empleado_service.cliente;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import ar.edu.palermo.empleado_service.dto.SucursalInfoDTO;

import java.util.Optional;

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

    public Optional<SucursalInfoDTO> obtenerPorId(Integer idSucursal) {
        try {
            SucursalInfoDTO dto = restTemplate.getForObject(
                "http://localhost:8086/sucursales/" + idSucursal,
                SucursalInfoDTO.class
            );
            return Optional.ofNullable(dto);
        } catch (RestClientException ignored) {
            return Optional.empty();
        }
    }

}
