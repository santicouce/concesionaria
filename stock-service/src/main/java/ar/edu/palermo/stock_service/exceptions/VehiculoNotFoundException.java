package ar.edu.palermo.stock_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VehiculoNotFoundException extends RuntimeException {
    public VehiculoNotFoundException(String mensaje) {
        super(mensaje);
    }
}
