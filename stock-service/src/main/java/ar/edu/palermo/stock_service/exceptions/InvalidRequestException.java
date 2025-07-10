package ar.edu.palermo.stock_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String mensaje) {
        super(mensaje);
    }
}
