package ar.edu.palermo.concesionaria.dominio;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nombre;
    private String direccion;
    private String localidad;
    private String provincia;
    private String pais;
    private Integer diasEntregaCliente;
    private Integer diasEntregaDesdeCentral;
    private Boolean esCentral = false;
    private LocalDate fechaApertura;

    // Constructores
    public Sucursal() {
    }

    public Sucursal(String nombre, String direccion, String localidad, String provincia, String pais, LocalDate fechaApertur, Integer diasEntregaCliente, Integer diasEntregaDesdeCentral) {
        setNombre(nombre);
        setDireccion(direccion);
        setLocalidad(localidad);
        setProvincia(provincia);
        setPais(pais);
        setFechaApertura(fechaApertura);
        setDiasEntregaCliente(diasEntregaCliente);
        setDiasEntregaDesdeCentral(diasEntregaDesdeCentral);
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Integer getDiasEntregaCliente() {
    return diasEntregaCliente;
    }

    public void setDiasEntregaCliente(Integer diasEntregaCliente) {
        this.diasEntregaCliente = diasEntregaCliente;
    }

    public Integer getDiasEntregaDesdeCentral() {
        return diasEntregaDesdeCentral;
    }

    public void setDiasEntregaDesdeCentral(Integer diasEntregaDesdeCentral) {
        this.diasEntregaDesdeCentral = diasEntregaDesdeCentral;
    }

    public Boolean getEsCentral() {
        return esCentral;
    }

    public void setEsCentral(Boolean esCentral) {
        this.esCentral = esCentral;
    }

}
