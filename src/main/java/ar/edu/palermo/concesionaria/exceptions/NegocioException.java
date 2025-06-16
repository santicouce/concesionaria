package ar.edu.palermo.concesionaria.exceptions;

public class NegocioException extends RuntimeException {
    public NegocioException(String mensaje) {
        super(mensaje);
    }
}
