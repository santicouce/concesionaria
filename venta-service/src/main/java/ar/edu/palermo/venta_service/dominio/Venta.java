package ar.edu.palermo.venta_service.dominio;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer clienteId;
    private Integer empleadoId;
    private Integer vehiculoId;

    private LocalDate fecha;
    private Double monto;
    private Integer diasEntrega;

    // Constructores
    public Venta() {
    }

    public Venta(Integer clienteId, Integer empleadoId, Integer vehiculoId, LocalDate fecha, Double monto, Integer diasEntrega) {
        setClienteId(clienteId);
        setEmpleadoId(empleadoId);
        setVehiculoId(vehiculoId);
        setFecha(fecha);
        setMonto(monto);
        setDiasEntrega(diasEntrega);
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Integer empleadoId) {
        this.empleadoId = empleadoId;
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

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Integer getDiasEntrega() {
        return diasEntrega;
    }

    public void setDiasEntrega(Integer diasEntrega) {
        this.diasEntrega = diasEntrega;
    }
}
