package ar.edu.palermo.serviciomecanico_service.dto;

import java.time.LocalDate;

/**
 * DTO para entrada/salida de datos de un Servicio Mecánico,
 */
public class ServicioMecanicoDTO {

    private Integer id;
    private Integer vehiculoId;
    private LocalDate fecha;
    private Integer kilometraje;
    private boolean enGarantia;

    public ServicioMecanicoDTO() {
    }

    public ServicioMecanicoDTO(Integer id,
                               boolean enGarantia,
                               Integer vehiculoId,
                               LocalDate fecha,
                               Integer kilometraje) {
        this.id = id;
        this.enGarantia = enGarantia;
        this.vehiculoId = vehiculoId;
        this.fecha = fecha;
        this.kilometraje = kilometraje;
    }

    // Getters y setters

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getEnGarantia() {
        return this.enGarantia;
    }
    public void setEnGarantia(boolean enGarantia) {
        this.enGarantia = enGarantia;
    }

    public Integer getVehiculoId() {
        return vehiculoId;
    }
    public void setVehiculoId(Integer vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getKilometraje() {
        return kilometraje;
    }
    public void setKilometraje(Integer kilometraje) {
        this.kilometraje = kilometraje;
    }
}
