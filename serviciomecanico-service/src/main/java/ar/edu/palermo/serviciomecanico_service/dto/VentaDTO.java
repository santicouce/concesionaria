package ar.edu.palermo.serviciomecanico_service.dto;

import java.time.LocalDate;

public class VentaDTO {
    private Integer id;
    private Integer clienteId;
    private Integer empleadoId;
    private Integer vehiculoId;
    private LocalDate fecha;
    private Double monto;
    private Integer diasEntrega;

    // Getters y Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
