package ar.edu.palermo.venta_service.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjetoRelacionadoNoEncontradoException extends RuntimeException {
    public ObjetoRelacionadoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
