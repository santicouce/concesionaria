package ar.edu.palermo.venta_service.dto;

public class StockInfoDTO {
    private Integer id;
    private Integer vehiculoId;
    private Integer sucursalId;
    private Integer cantidad;

    public StockInfoDTO() {}

    public StockInfoDTO(Integer id, Integer vehiculoId, Integer sucursalId, Integer cantidad) {
        this.id  = id;
        this.vehiculoId = vehiculoId;
        this.sucursalId = sucursalId;
        this.cantidad = cantidad;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Integer vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public Integer getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(Integer sucursalId) {
        this.sucursalId = sucursalId;
    }
}
