// src/main/java/com/web/taller1/controllers/AuthController.java
package com.web.taller1.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.taller1.entities.Usuario;
import com.web.taller1.repositories.UsuarioRepository;
import com.web.taller1.security.JwtUtil;
import com.web.taller1.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @Value("${frontend.url}")
    private String frontendUrl;

    @CrossOrigin(origins = "${frontend.url}")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        Optional<Usuario> user = usuarioRepository.findByEmail(usuario.getEmail());
        if (user.isPresent()) {
            // Log para ver la contraseña recibida
            System.out.println("Contraseña recibida: " + usuario.getPassword());

            // Autenticar usuario
            String isAuthenticated = authService.authenticateUsuario(usuario.getEmail(), usuario.getPassword());

            // Log para ver la contraseña encriptada recuperada de la base de datos
            System.out.println("Contraseña encriptada recuperada de la base de datos: " + user.get().getPassword());

            if (isAuthenticated.equals(user.get().getPassword())) {
                String token = jwtUtil.generateToken(user.get().getEmail());
                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

    @CrossOrigin(origins = "${frontend.url}")
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Usuario usuario) {
        String token = authService.registerUsuario(usuario);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}