package com.web.taller1.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;


@Entity
public class UsuarioArrendador extends Usuario {
    @OneToMany(mappedBy = "arrendador", cascade = CascadeType.ALL)
    private List<SolicitudArrendamiento> solicitudes;

    // Getters y setters

    public List<SolicitudArrendamiento> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<SolicitudArrendamiento> solicitudes) {
        this.solicitudes = solicitudes;
    }
}
