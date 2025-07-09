package ar.edu.palermo.empleado_service.dto;

public class EmpleadoRequest {
    private String nombre;
    private String apellido;
    private String legajo;
    private String rol;
    private Integer sucursalId;

    // Getters y setters
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
    public String getLegajo(String legajo){
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
