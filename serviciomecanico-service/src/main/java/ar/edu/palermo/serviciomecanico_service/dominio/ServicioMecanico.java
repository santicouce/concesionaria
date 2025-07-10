package ar.edu.palermo.serviciomecanico_service.dominio;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class ServicioMecanico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer vehiculoId;

    private LocalDate fecha;
    private Integer kilometraje;
    private Boolean enGarantia;
    

    // Constructores
    public ServicioMecanico() {
    }

    public ServicioMecanico(Integer vehiculoId, LocalDate fecha, Integer kilometraje, Boolean enGarantia) {
        setVehiculoId(vehiculoId);
        setFecha(fecha);
        setKilometraje(kilometraje);
        setEnGarantia(enGarantia);
    }

    // Getters y setters
    public Integer getId() {
        return id;
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

    public Boolean getEnGarantia() {
        return this.enGarantia;
    }

    public void setEnGarantia(Boolean enGarantia) {
        this.enGarantia = enGarantia;
    }
}
