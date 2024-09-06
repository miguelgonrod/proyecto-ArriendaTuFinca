package com.web.taller1.DTO;

import com.web.taller1.entities.Propiedad;

public class PropiedadDTO {

    private Long id;
    private String direccion;
    private String descripcion;
    private Double precio;
    private String municipio;
    private Integer numeroPersonas;
    private String estado;
    private Long usuarioId;  // FK hacia Usuario

    // Constructor vacío (necesario para frameworks como JPA)
    public PropiedadDTO() {}

    // Constructor que recibe una entidad Propiedad
    public PropiedadDTO(Propiedad propiedad) {
        this.id = propiedad.getId();
        this.direccion = propiedad.getDireccion();
        this.descripcion = propiedad.getDescripcion();
        this.precio = propiedad.getPrecio();
        this.municipio = propiedad.getMunicipio();
        this.numeroPersonas = propiedad.getNumeroPersonas();
        this.estado = propiedad.getEstado();

        // Asegúrate de que la relación no sea nula antes de acceder a ella
        if (propiedad.getUsuario() != null) {
            this.usuarioId = propiedad.getUsuario().getId();
        }
    }

    // Getters y Setters
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

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public Integer getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(Integer numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}

