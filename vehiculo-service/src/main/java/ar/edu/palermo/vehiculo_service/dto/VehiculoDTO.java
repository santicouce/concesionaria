package ar.edu.palermo.vehiculo_service.dto;

/**
 * DTO para transferencia de datos de Vehículo.
 */
public class VehiculoDTO {

    private Integer id;

    private String numeroChasis;
    private String marca;
    private String modelo;
    private Integer anio;
    private String tipo;

    public VehiculoDTO() { }

    public VehiculoDTO(Integer id,
                       String numeroChasis,
                       String marca,
                       String modelo,
                       Integer anio,
                       String tipo) {
        this.id = id;
        this.numeroChasis = numeroChasis;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.tipo = tipo;
    }

    // Getters y setters

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
