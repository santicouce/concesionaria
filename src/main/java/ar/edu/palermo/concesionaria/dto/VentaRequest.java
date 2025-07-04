package ar.edu.palermo.concesionaria.dto;

import java.time.LocalDate;

public class VentaRequest {
    // Creo esta clase para poder recibir los primary keys de las entidades relacionadas
    // y asi luego poder buscar los objetos correspondientes en la base de datos.
    private Integer clienteId;
    private Integer vehiculoId;
    private Integer empleadoId;
    private LocalDate fecha;
    private Double monto;

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

    public Integer getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Integer empleadoId) {
        this.empleadoId = empleadoId;
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
}
