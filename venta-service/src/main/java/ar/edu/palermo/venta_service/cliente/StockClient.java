package ar.edu.palermo.venta_service.cliente;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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
            StockInfoDTO[] arr = restTemplate.getForObject(
                uri.toUriString(),
                StockInfoDTO[].class
            );
            if (arr != null && arr.length > 0) {
                return arr[0];
            } else {
                return new StockInfoDTO(0, vehiculoId, sucursalId, 0);
            }
        } catch (RestClientException e) {
            System.err.println("Error al consultar stock: " + e.getMessage());
            return new StockInfoDTO(0, vehiculoId, sucursalId, 0);
        }
    }

    public StockInfoDTO findByVehiculoInCentral(Integer vehiculoId) {
        UriComponents uri = UriComponentsBuilder
                .fromUriString(STOCK_URL + "/central")
                .queryParam("vehiculoId", vehiculoId)
                .build()
                .encode();

        try {
            StockInfoDTO[] arr = restTemplate.getForObject(
                uri.toUriString(),
                StockInfoDTO[].class
            );
            if (arr != null && arr.length > 0) {
                return arr[0];
            } else {
                return new StockInfoDTO(0, vehiculoId, null, 0);
            }
        } catch (RestClientException e) {
            return new StockInfoDTO(0, vehiculoId, null, 0);
        }
    }

    public boolean decrementStock(Integer stockId) {
        String url = STOCK_URL + "/" + stockId + "/venta";
        try {
            restTemplate.postForEntity(url, null, Void.class);
            return true;
        } catch (RestClientException e) {
            return false;
        }
    }
}
