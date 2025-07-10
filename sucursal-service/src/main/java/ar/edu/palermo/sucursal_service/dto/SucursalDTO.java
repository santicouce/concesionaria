package ar.edu.palermo.sucursal_service.dto;

import java.time.LocalDate;

/**
 * DTO para transferencia de datos de una Sucursal.
 */
public class SucursalDTO {

    private Integer id;
    private String nombre;
    private String direccion;
    private String localidad;
    private String provincia;
    private String pais;
    private Integer diasEntregaCliente;
    private Integer diasEntregaDesdeCentral;
    private Boolean esCentral;
    private LocalDate fechaApertura;

    public SucursalDTO() { }

    public SucursalDTO(Integer id,
                       String nombre,
                       String direccion,
                       String localidad,
                       String provincia,
                       String pais,
                       Integer diasEntregaCliente,
                       Integer diasEntregaDesdeCentral,
                       Boolean esCentral,
                       LocalDate fechaApertura) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.pais = pais;
        this.diasEntregaCliente = diasEntregaCliente;
        this.diasEntregaDesdeCentral = diasEntregaDesdeCentral;
        this.esCentral = esCentral;
        this.fechaApertura = fechaApertura;
    }

    // Getters y setters

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }
    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }
}
