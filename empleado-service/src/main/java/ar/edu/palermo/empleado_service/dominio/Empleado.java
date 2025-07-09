package ar.edu.palermo.empleado_service.dominio;

import jakarta.persistence.*;

@Entity
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nombre;
    private String apellido;
    private String legajo;
    private String rol;

    private Integer sucursalId;

    // Constructores
    public Empleado() {
    }

    public Empleado(String nombre, String apellido, String legajo, String rol, Integer sucursalId) {
        setNombre(nombre);
        setApellido(apellido);
        setLegajo(legajo);
        setRol(rol);
        setSucursalId(sucursalId);
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(Integer sucursalId) {
        this.sucursalId = sucursalId;
    }
}
