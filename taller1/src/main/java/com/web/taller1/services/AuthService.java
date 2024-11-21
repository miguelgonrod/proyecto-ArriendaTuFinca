package com.web.taller1.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.taller1.entities.Usuario;
import com.web.taller1.repositories.UsuarioRepository;
import com.web.taller1.security.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String registerUsuario(String email, String password, String role, String username, String telefono) {
        // Asignar rol predeterminado si no se proporciona
        if (role == null || role.isEmpty()) {
            role = "arrendador";
        }
    
        // Codificar la contraseña
        String encodedPassword = passwordEncoder.encode(password);
    
        // Crear y guardar el usuario
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setPassword(encodedPassword);
        usuario.setRole(role);
        usuario.setNombre(username);
        usuario.setTelefono(telefono);
    
        Usuario savedUsuario = usuarioRepository.save(usuario);
    
        // Generar el JWT
        return jwtUtil.generateToken(savedUsuario.getEmail(), savedUsuario.getRole());
    }

    public String login(String email, String password) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            // Validar contraseña
            if (passwordEncoder.matches(password, usuario.getPassword())) {
                // Generar JWT
                return jwtUtil.generateToken(usuario.getEmail(), usuario.getRole());
            } else {
                throw new IllegalArgumentException("Contraseña incorrecta");
            }
        } else {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
    }
}
