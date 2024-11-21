package com.web.taller1.services;

import com.web.taller1.DTO.UsuarioDTO;
import com.web.taller1.entities.Usuario;
import com.web.taller1.repositories.UsuarioRepository;
import com.web.taller1.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Crear un usuario
    public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO) {
        validarEmailUnico(usuarioDTO.getEmail());
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword())); // Encriptar contraseña
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setRole("USER"); // Asignar rol por defecto
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return new UsuarioDTO(nuevoUsuario);
    }

    // Autenticar un usuario y generar token
    public String authenticateUsuario(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));

        if (passwordEncoder.matches(password, usuario.getPassword())) {
            return jwtUtil.generateToken(usuario.getEmail(), usuario.getRole()); // Incluye rol en el token
        } else {
            throw new RuntimeException("Contraseña incorrecta");
        }
    }

    // Obtener todos los usuarios
    public List<UsuarioDTO> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(UsuarioDTO::new).collect(Collectors.toList());
    }

    // Obtener un usuario por ID
    public UsuarioDTO getUsuarioById(Long id) {
        Usuario usuario = findUsuarioById(id);
        return new UsuarioDTO(usuario);
    }

    // Actualizar un usuario
    public UsuarioDTO updateUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = findUsuarioById(id);
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setEmail(usuarioDTO.getEmail());

        if (usuarioDTO.getPassword() != null && !usuarioDTO.getPassword().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword())); // Encriptar nueva contraseña
        }

        usuario.setTelefono(usuarioDTO.getTelefono());
        Usuario usuarioActualizado = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuarioActualizado);
    }

    // Eliminar un usuario
    public void deleteUsuario(Long id) {
        Usuario usuario = findUsuarioById(id);
        usuarioRepository.delete(usuario);
    }

    // Método privado para reutilizar lógica de búsqueda por ID
    private Usuario findUsuarioById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    // Validar que el email no esté duplicado
    private void validarEmailUnico(String email) {
        if (usuarioRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("El email ya está en uso");
        }
    }
}
