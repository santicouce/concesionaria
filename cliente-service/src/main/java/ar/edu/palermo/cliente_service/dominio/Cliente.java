package ar.edu.palermo.cliente_service.dominio;

import jakarta.persistence.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;

    // Constructores
    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String dni, String telefono) {
        setNombre(nombre);
        setApellido(apellido);
        setDni(dni);
        setTelefono(telefono);
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
