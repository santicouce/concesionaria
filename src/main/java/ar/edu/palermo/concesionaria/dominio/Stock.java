package ar.edu.palermo.concesionaria.dominio;

import jakarta.persistence.*;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Sucursal sucursal;

    @ManyToOne
    private Vehiculo vehiculo;

    private Integer cantidad;

    public Stock() {}

    public Stock(Sucursal sucursal, Vehiculo vehiculo, Integer cantidad) {
        setSucursal(sucursal);
        setVehiculo(vehiculo);
        setCantidad(cantidad);
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
