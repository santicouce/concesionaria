package ar.edu.palermo.venta_service.dto;

import java.time.LocalDate;

public class VentaDTO {
    private Integer clienteId;
    private Integer vehiculoId;
    private Integer empleadoId;
    private LocalDate fecha;
    private Double monto;

    public VentaDTO() { }

    public VentaDTO(Integer clienteId,
                    Integer vehiculoId,
                    Integer empleadoId,
                    LocalDate fecha,
                    Double monto) {
        this.clienteId   = clienteId;
        this.vehiculoId  = vehiculoId;
        this.empleadoId  = empleadoId;
        this.fecha       = fecha;
        this.monto       = monto;
    }

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
