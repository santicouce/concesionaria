package ar.edu.palermo.concesionaria.dominio;

import jakarta.persistence.*;

@Entity
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String numeroChasis;
    private String marca;
    private String modelo;
    private Integer anio;
    private String tipo;

    // Constructores
    public Vehiculo() {
    }

    public Vehiculo(String numeroChasis, String marca, String modelo, Integer anio, String tipo) {
        setNumeroChasis(numeroChasis);
        setMarca(marca);
        setModelo(modelo);
        setAnio(anio);
        setTipo(tipo);
    }

    public Vehiculo(String numeroChasis, String marca, String modelo, Integer anio) {
        setNumeroChasis(numeroChasis);
        setMarca(marca);
        setModelo(modelo);
        setAnio(anio);
        setTipo("automovil"); // Valor por defecto
    }

    // Getters y setters

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public Integer getId() {
        return id;
    }

    public String getNumeroChasis() {
        return numeroChasis;
    }

    public void setNumeroChasis(String numeroChasis) {
        this.numeroChasis = numeroChasis;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

}
