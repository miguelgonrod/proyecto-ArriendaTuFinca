package com.web.taller1.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.taller1.DTO.LoginRequest;
import com.web.taller1.DTO.LoginResponse;
import com.web.taller1.DTO.RegisterRequest;
import com.web.taller1.entities.Usuario;
import com.web.taller1.repositories.UsuarioRepository;
import com.web.taller1.security.JwtUtil;
import com.web.taller1.services.AuthService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest registerRequest) {
        System.out.println("Datos recibidos: " + registerRequest);
        
        // Registrar usuario con el rol especificado
        String token = authService.registerUsuario(registerRequest.getEmail(), 
                                                   registerRequest.getPassword(), 
                                                   registerRequest.getRole(),
                                                   registerRequest.getUsername(),
                                                   registerRequest.getTelefono());
    
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}
