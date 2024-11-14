    package com.web.taller1.services;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.stream.Collectors;

    import com.web.taller1.repositories.UsuarioRepository;
    import com.web.taller1.DTO.UsuarioDTO;
    import com.web.taller1.entities.Usuario;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;
    import com.web.taller1.security.JwtUtil;

    import com.web.taller1.security.SecurityConfig;

    @Service
    public class UsuarioService {

        @Autowired
        private UsuarioRepository usuarioRepository;

        @Autowired
        private JwtUtil jwtUtil;

        @Autowired
        private PasswordEncoder passwordEncoder;  // Inject PasswordEncoder

        public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO) {
            Usuario usuario = new Usuario();
            usuario.setNombre(usuarioDTO.getNombre());
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));  // Encriptar contraseña
            usuario.setTelefono(usuarioDTO.getTelefono());
            Usuario nuevoUsuario = usuarioRepository.save(usuario);
            return new UsuarioDTO(nuevoUsuario);
        }
    
        // Método para autenticar al usuario
        public String authenticateUsuario(String email, String password) {
            Usuario usuario = usuarioRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));
            
            if (passwordEncoder.matches(password, usuario.getPassword())) {
                return jwtUtil.generateToken(usuario.getEmail());  // Genera y retorna el token JWT
            } else {
                throw new RuntimeException("Contraseña incorrecta");
            }
        }

        public List<UsuarioDTO> getAllUsuarios() {
            List<Usuario> usuarios = usuarioRepository.findAll();
            return usuarios.stream().map(UsuarioDTO::new).collect(Collectors.toList());
        }

        public UsuarioDTO getUsuarioById(Long id) {
            Usuario usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
            return new UsuarioDTO(usuario);
        }

        public UsuarioDTO updateUsuario(Long id, UsuarioDTO usuarioDTO) {
            Usuario usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
            usuario.setNombre(usuarioDTO.getNombre());
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setPassword(usuarioDTO.getPassword());
            usuario.setTelefono(usuarioDTO.getTelefono());
            Usuario usuarioActualizado = usuarioRepository.save(usuario);
            return new UsuarioDTO(usuarioActualizado);
        }

        public void deleteUsuario(Long id) {
            Usuario usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
            usuarioRepository.delete(usuario);
        }
    }
