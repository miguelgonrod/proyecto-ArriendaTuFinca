package com.web.taller1.DTO;

import java.util.Date;

public class SolicitudArrendamientoDTO {

    private Long id;
    private Long arrendadorId;
    private Long propiedadId;
    private String estado;
    private Date fechaSolicitud;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArrendadorId() {
        return arrendadorId;
    }

    public void setArrendadorId(Long arrendadorId) {
        this.arrendadorId = arrendadorId;
    }

    public Long getPropiedadId() {
        return propiedadId;
    }

    public void setPropiedadId(Long propiedadId) {
        this.propiedadId = propiedadId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
}