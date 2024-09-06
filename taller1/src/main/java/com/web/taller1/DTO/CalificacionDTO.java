package com.web.taller1.DTO;

import com.web.taller1.entities.Calificacion;

public class CalificacionDTO {

    private Long id;
    private Integer puntuacion;
    private String comentario;  // Asegúrate de que el campo comentario esté definido
    private Long usuarioId;
    private Long propiedadId;

    public CalificacionDTO() {}
    
    public CalificacionDTO(Calificacion calificacion) {
        this.id = calificacion.getId();
        this.puntuacion = calificacion.getPuntuacion();
        this.comentario = calificacion.getComentario();

        if (calificacion.getUsuario() != null) {
            this.usuarioId = calificacion.getUsuario().getId();
        }

        if (calificacion.getPropiedad() != null) {
            this.propiedadId = calificacion.getPropiedad().getId();
        }
    }
    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;  // Getter para comentario
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;  // Setter para comentario
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getPropiedadId() {
        return propiedadId;
    }

    public void setPropiedadId(Long propiedadId) {
        this.propiedadId = propiedadId;
    }
}

