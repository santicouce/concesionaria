package ar.edu.palermo.concesionaria.dto;

import java.time.LocalDate;

public class ServicioMecanicoRequest {
    // Creo esta clase para poder recibir los primary keys de las entidades relacionadas
    // y asi luego poder buscar los objetos correspondientes en la base de datos.
    private Integer clienteId;
    private Integer vehiculoId;
    private Integer kilometraje;
    private LocalDate fecha;

    // Getters y Setters

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Integer vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public Integer getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(Integer kilometraje) {
        this.kilometraje = kilometraje;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
