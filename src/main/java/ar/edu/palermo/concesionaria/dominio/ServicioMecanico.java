package ar.edu.palermo.concesionaria.dominio;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class ServicioMecanico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    private LocalDate fecha;
    private Integer kilometraje;
    private Boolean enGarantia;

    // Constructores
    public ServicioMecanico() {
    }

    public ServicioMecanico(Cliente cliente, Vehiculo vehiculo, LocalDate fecha, Integer kilometraje, Boolean enGarantia) {
        setCliente(cliente);
        setVehiculo(vehiculo);
        setFecha(fecha);
        setKilometraje(kilometraje);
        setEnGarantia(enGarantia);
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
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
        return enGarantia;
    }

    public void setEnGarantia(Boolean enGarantia) {
        this.enGarantia = enGarantia;
    }
}
