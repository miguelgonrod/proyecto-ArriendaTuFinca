package com.web.taller1.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;


@Entity
public class UsuarioArrendatario extends Usuario {
    @OneToMany(mappedBy = "arrendatario", cascade = CascadeType.ALL)
    private List<Propiedad> propiedades;

    // Getters y setters
    public List<Propiedad> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(List<Propiedad> propiedades) {
        this.propiedades = propiedades;
    }
}
