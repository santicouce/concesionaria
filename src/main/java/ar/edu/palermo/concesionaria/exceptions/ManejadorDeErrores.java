package ar.edu.palermo.concesionaria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ManejadorDeErrores {

    @ExceptionHandler({DatosInvalidosException.class, NegocioException.class})
    public ResponseEntity<Map<String, Object>> manejarDatosInvalidos(DatosInvalidosException ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("error", "Datos inválidos");
        respuesta.put("mensaje", ex.getMessage());
        respuesta.put("fecha", LocalDateTime.now());
        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }
}
