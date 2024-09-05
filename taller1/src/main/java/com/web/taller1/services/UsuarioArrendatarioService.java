package com.web.taller1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.web.taller1.repositories.UsuarioArrendatarioRepository;
import com.web.taller1.DTO.UsuarioArrendatarioDTO;
import com.web.taller1.entities.UsuarioArrendatario;
import com.web.taller1.mapper.UsuarioArrendatarioMapper;

@Service
public class UsuarioArrendatarioService {

    @Autowired
    private UsuarioArrendatarioRepository usuarioArrendatarioRepository;

    @Autowired
    private UsuarioArrendatarioMapper usuarioArrendatarioMapper;

    // Crear un nuevo arrendatario
    public UsuarioArrendatarioDTO createArrendatario(UsuarioArrendatarioDTO arrendatarioDTO) {
        UsuarioArrendatario arrendatario = usuarioArrendatarioMapper.toEntity(arrendatarioDTO);
        UsuarioArrendatario nuevoArrendatario = usuarioArrendatarioRepository.save(arrendatario);
        return usuarioArrendatarioMapper.toDTO(nuevoArrendatario);
    }

    // Obtener todos los arrendatarios
    public List<UsuarioArrendatarioDTO> getAllArrendatarios() {
        List<UsuarioArrendatario> arrendatarios = usuarioArrendatarioRepository.findAll();
        return arrendatarios.stream().map(usuarioArrendatarioMapper::toDTO).collect(Collectors.toList());
    }

    // Obtener arrendatario por ID
    public UsuarioArrendatarioDTO getArrendatarioById(Long id) {
        UsuarioArrendatario arrendatario = usuarioArrendatarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Arrendatario no encontrado con id: " + id));
        return usuarioArrendatarioMapper.toDTO(arrendatario);
    }

    public UsuarioArrendatarioDTO updateArrendatario(Long id, UsuarioArrendatarioDTO arrendatarioDTO) {
        UsuarioArrendatario arrendatario = usuarioArrendatarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Arrendatario no encontrado con id: " + id));

        // Actualiza los campos del arrendatario
        arrendatario.setNombre(arrendatarioDTO.getNombre());
        arrendatario.setEmail(arrendatarioDTO.getEmail());
        arrendatario.setPassword(arrendatarioDTO.getPassword());

        // Guardar los cambios en la base de datos
        UsuarioArrendatario arrendatarioActualizado = usuarioArrendatarioRepository.save(arrendatario);
        return usuarioArrendatarioMapper.toDTO(arrendatarioActualizado);
    }

    public void deleteArrendatario(Long id) {
        UsuarioArrendatario arrendatario = usuarioArrendatarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Arrendatario no encontrado con id: " + id));
        
        usuarioArrendatarioRepository.delete(arrendatario); // Eliminar el arrendatario de la base de datos
    }
}

