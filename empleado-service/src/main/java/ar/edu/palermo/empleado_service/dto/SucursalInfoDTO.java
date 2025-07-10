package ar.edu.palermo.empleado_service.dto;

public class SucursalInfoDTO {
    private Integer id;
    private Boolean esCentral;
    private Integer diasEntregaCliente;
    private Integer diasEntregaDesdeCentral;

    public SucursalInfoDTO() {}

    public SucursalInfoDTO(Integer id, Boolean esCentral) {
        this.id = id;
        this.esCentral = esCentral;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getEsCentral() {
        return esCentral;
    }

    public void setEsCentral(Boolean esCentral) {
        this.esCentral = esCentral;
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
}
