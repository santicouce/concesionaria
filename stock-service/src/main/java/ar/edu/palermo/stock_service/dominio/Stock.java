package ar.edu.palermo.stock_service.dominio;

import jakarta.persistence.*;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer sucursalId;
    private Integer vehiculoId;

    private Integer cantidad;

    public Stock() {}

    public Stock(Integer sucursalId, Integer vehiculoId, Integer cantidad) {
        setSucursalId(sucursalId);
        setVehiculoId(vehiculoId);
        setCantidad(cantidad);
    }

    // Getters y Setters
    public Integer getId() {
        return id;
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
