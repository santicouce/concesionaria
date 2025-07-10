package ar.edu.palermo.stock_service.dto;

/**
 * DTO para transferencia de datos de Stock.
 */
public class StockDTO {

    private Integer id;
    private Integer sucursalId;
    private Integer vehiculoId;
    private Integer cantidad;

    public StockDTO() { }

    public StockDTO(Integer id,
                    Integer sucursalId,
                    Integer vehiculoId,
                    Integer cantidad) {
        this.id = id;
        this.sucursalId = sucursalId;
        this.vehiculoId = vehiculoId;
        this.cantidad = cantidad;
    }

    // Getters y setters

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSucursalId() {
        return sucursalId;
    }
    public void setSucursalId(Integer sucursalId) {
        this.sucursalId = sucursalId;
    }

    public Integer getVehiculoId() {
        return vehiculoId;
    }
    public void setVehiculoId(Integer vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
