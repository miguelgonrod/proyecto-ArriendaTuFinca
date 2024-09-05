package com.web.taller1.DTO;

public class PropiedadDTO {

    private Long id;
    private String direccion;
    private String descripcion;
    private Double precio;
    private Long arrendatarioId;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Long getArrendatarioId() {
        return arrendatarioId;
    }

    public void setArrendatarioId(Long arrendatarioId) {
        this.arrendatarioId = arrendatarioId;
    }
}
