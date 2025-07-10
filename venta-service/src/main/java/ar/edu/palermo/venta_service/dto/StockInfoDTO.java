package ar.edu.palermo.venta_service.dto;

public class StockInfoDTO {
    private Integer id;
    private Integer cantidad;

    public StockInfoDTO() {}

    public StockInfoDTO(Integer id, Integer cantidad) {
        this.id  = id;
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
}
