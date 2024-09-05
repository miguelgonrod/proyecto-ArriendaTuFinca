package com.web.taller1.DTO;

import java.util.List;

public class UsuarioArrendadorDTO {

    private Long id;
    private String nombre;
    private String email;
    private String password;
    
    // Opcional: Lista de solicitudes que este arrendador ha hecho
    private List<SolicitudArrendamientoDTO> solicitudes;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SolicitudArrendamientoDTO> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<SolicitudArrendamientoDTO> solicitudes) {
        this.solicitudes = solicitudes;
    }
}
