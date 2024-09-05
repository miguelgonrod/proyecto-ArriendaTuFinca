package com.web.taller1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.web.taller1.repositories.UsuarioArrendadorRepository;
import com.web.taller1.DTO.UsuarioArrendadorDTO;
import com.web.taller1.entities.UsuarioArrendador;
import com.web.taller1.mapper.UsuarioArrendadorMapper;

@Service
public class UsuarioArrendadorService {

    @Autowired
    private UsuarioArrendadorRepository arrendadorRepository;

    @Autowired
    private UsuarioArrendadorMapper usuarioArrendadorMapper;

    // Crear un nuevo arrendador
    public UsuarioArrendadorDTO createArrendador(UsuarioArrendadorDTO arrendadorDTO) {
        UsuarioArrendador arrendador = usuarioArrendadorMapper.toEntity(arrendadorDTO);
        UsuarioArrendador nuevoArrendador = arrendadorRepository.save(arrendador);
        return usuarioArrendadorMapper.toDTO(nuevoArrendador);
    }

    // Obtener todos los arrendadores
    public List<UsuarioArrendadorDTO> getAllArrendadores() {
        List<UsuarioArrendador> arrendadores = arrendadorRepository.findAll();
        return arrendadores.stream().map(usuarioArrendadorMapper::toDTO).collect(Collectors.toList());
    }

    // Obtener arrendador por ID
    public UsuarioArrendadorDTO getArrendadorById(Long id) {
        UsuarioArrendador arrendador = arrendadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Arrendador no encontrado con id: " + id));
        return usuarioArrendadorMapper.toDTO(arrendador);
    }

    // Actualizar un arrendador
    public UsuarioArrendadorDTO updateArrendador(Long id, UsuarioArrendadorDTO arrendadorDTO) {
        UsuarioArrendador arrendador = arrendadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Arrendador no encontrado con id: " + id));

        arrendador.setNombre(arrendadorDTO.getNombre());
        arrendador.setEmail(arrendadorDTO.getEmail());
        arrendador.setPassword(arrendadorDTO.getPassword());

        UsuarioArrendador arrendadorActualizado = arrendadorRepository.save(arrendador);
        return usuarioArrendadorMapper.toDTO(arrendadorActualizado);
    }

    // Eliminar un arrendador
    public void deleteArrendador(Long id) {
        UsuarioArrendador arrendador = arrendadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Arrendador no encontrado con id: " + id));
        arrendadorRepository.delete(arrendador);
    }
}
