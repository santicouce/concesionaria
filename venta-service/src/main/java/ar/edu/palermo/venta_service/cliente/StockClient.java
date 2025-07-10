package ar.edu.palermo.venta_service.cliente;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import ar.edu.palermo.venta_service.dto.StockInfoDTO;

@Service
public class StockClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String STOCK_URL = "http://localhost:8087/stock";

    public StockInfoDTO findBySucursalAndVehiculo(Integer vehiculoId, Integer sucursalId) {
        UriComponents uri = UriComponentsBuilder
                .fromUriString(STOCK_URL)
                .queryParam("vehiculoId", vehiculoId)
                .queryParam("sucursalId", sucursalId)
                .build()
                .encode();

        try {
            StockInfoDTO resp = restTemplate.getForObject(
                uri.toUriString(),
                StockInfoDTO.class
            );
            // si el servicio te devuelve null (body vacío), devolvemos un DTO con cantidad=0
            return (resp != null ? resp : new StockInfoDTO(0,0));
        } catch (RestClientException e) {
            // en caso de error devolvemos también un DTO con cantidad=0
            return new StockInfoDTO(0,0);
        }
    }

    public StockInfoDTO findByVehiculoInCentral(Integer vehiculoId) {
        UriComponents uri = UriComponentsBuilder
                .fromUriString(STOCK_URL + "/central")
                .queryParam("vehiculoId", vehiculoId)
                .build()
                .encode();

        try {
            StockInfoDTO resp = restTemplate.getForObject(
                uri.toUriString(),
                StockInfoDTO.class
            );
            return (resp != null ? resp : new StockInfoDTO(0,0));
        } catch (RestClientException e) {
            return new StockInfoDTO(0,0);
        }
    }

    public boolean decrementStock(Integer stockId) {
        String url = STOCK_URL + "/" + stockId + "/venta";
        try {
            ResponseEntity<Void> response =
                restTemplate.postForEntity(url, null, Void.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (RestClientException e) {
            // 4xx/5xx
            return false;
        }
    }

    
}
