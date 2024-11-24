// src/main/java/com/web/taller1/services/AuthService.java
package com.web.taller1.services;

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

    public String registerUsuario(Usuario usuario) {
        // Codificar la contraseña antes de guardarla
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        
        // Guardar el nuevo usuario en la base de datos
        Usuario savedUsuario = usuarioRepository.save(usuario);
        
        // Generar el JWT para el usuario registrado
        return jwtUtil.generateToken(savedUsuario.getEmail());
    }

    public String authenticateUsuario(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));

       // Log para ver la contraseña recibida
       System.out.println("Contraseña recibida (sin encriptar): " + password);

       // Log para ver la contraseña encriptada recuperada de la base de datos
       System.out.println("Contraseña encriptada recuperada de la base de datos: " + usuario.getPassword());

       // Encriptar la contraseña recibida para comparación
       String encryptedPassword = passwordEncoder.encode(password);
       System.out.println("Contraseña encriptada para comparación: " + encryptedPassword);

        
        // Comparar la contraseña encriptada recibida con la almacenada en la base de datos
        if (passwordEncoder.matches(password, usuario.getPassword())) {
            System.out.println("Contraseña correcta");
            return jwtUtil.generateToken(usuario.getEmail());  // Genera y retorna el token JWT
        } else {
            throw new RuntimeException("Contraseña incorrecta");
        }
    }
}