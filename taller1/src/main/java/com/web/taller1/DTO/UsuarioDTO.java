package com.web.taller1.DTO;

import com.web.taller1.entities.Usuario;

public class UsuarioDTO {

    private Long id;
    private String nombre;
    private String email;
    private String password;
    private String telefono;
    private String role;

    // Constructor vacío (obligatorio para algunos frameworks como JPA)
    public UsuarioDTO() {}

    // Constructor que recibe una entidad Usuario
    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.email = usuario.getEmail();
        this.password = usuario.getPassword();  // Asegúrate de manejar esto con seguridad
        this.telefono = usuario.getTelefono();
        this.role = usuario.getRole();
    }

    // Getters y Setters
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", telefono='" + telefono + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
