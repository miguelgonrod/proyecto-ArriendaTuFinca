package com.web.taller1.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.web.taller1.DTO.PropiedadDTO;
import com.web.taller1.entities.Propiedad;
import com.web.taller1.entities.Usuario;
import com.web.taller1.repositories.PropiedadRepository;
import com.web.taller1.repositories.UsuarioRepository;
import org.springframework.data.jpa.domain.Specification;

@Service
public class PropiedadService {

    @Autowired
    private PropiedadRepository propiedadRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public PropiedadDTO createPropiedad(PropiedadDTO propiedadDTO) {
        // Crear una nueva instancia de Propiedad
        Propiedad propiedad = new Propiedad();
        
        // Asignar los valores desde el DTO a la entidad Propiedad
        propiedad.setDireccion(propiedadDTO.getDireccion());
        propiedad.setDescripcion(propiedadDTO.getDescripcion());
        propiedad.setPrecio(propiedadDTO.getPrecio());
        
        // Asignar el usuario si está presente
        if (propiedadDTO.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(propiedadDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + propiedadDTO.getUsuarioId()));
            propiedad.setUsuario(usuario);
        } else {
            throw new RuntimeException("El ID del usuario no puede ser nulo");
        }
        
        // Asignar valores adicionales
        propiedad.setMunicipio(propiedadDTO.getMunicipio());
        propiedad.setNumeroPersonas(propiedadDTO.getNumeroPersonas());
        propiedad.setEstado(propiedadDTO.getEstado());
        propiedad.setNombre(propiedadDTO.getNombre());  // Asignar propiedad nombre

        
        // Guardar la nueva propiedad en la base de datos
        Propiedad nuevaPropiedad = propiedadRepository.save(propiedad);
    
        // Convertir la entidad guardada en un DTO para la respuesta
        return convertToDTO(nuevaPropiedad);
    }

    public List<PropiedadDTO> getAllPropiedades() {
        List<Propiedad> propiedades = propiedadRepository.findAll();
        return propiedades.stream().map(PropiedadDTO::new).collect(Collectors.toList());
    }

    public PropiedadDTO getPropiedadById(Long id) {
        Propiedad propiedad = propiedadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propiedad no encontrada con id: " + id));
        return new PropiedadDTO(propiedad);
    }

    public List<PropiedadDTO> getPropiedadesByUsuarioId(Long usuarioId) {
        return propiedadRepository.findByUsuarioId(usuarioId).stream()
                .map(PropiedadDTO::new)
                .collect(Collectors.toList());
    }

    public PropiedadDTO updatePropiedad(Long id, PropiedadDTO propiedadDTO) {
        Propiedad propiedad = propiedadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propiedad no encontrada con id: " + id));
        propiedad.setDireccion(propiedadDTO.getDireccion());
        propiedad.setDescripcion(propiedadDTO.getDescripcion());
        propiedad.setPrecio(propiedadDTO.getPrecio());
        propiedad.setMunicipio(propiedadDTO.getMunicipio());
        propiedad.setNumeroPersonas(propiedadDTO.getNumeroPersonas());
        propiedad.setNombre(propiedadDTO.getNombre());  // Asignar propiedad nombre

        Propiedad propiedadActualizada = propiedadRepository.save(propiedad);
        return new PropiedadDTO(propiedadActualizada);
    }

    public void deletePropiedad(Long id) {
        Propiedad propiedad = propiedadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propiedad no encontrada con id: " + id));
        propiedadRepository.delete(propiedad);
    }

    public List<PropiedadDTO> buscarPropiedades(String nombre, String municipio, Integer numeroPersonas) {
        Specification<Propiedad> specs = Specification.where(null);
        
        if (nombre != null && !nombre.isEmpty()) {
            specs = specs.and((root, query, builder) -> 
                builder.like(builder.lower(root.get("nombre")), "%" + nombre.toLowerCase() + "%"));
        }
        if (municipio != null && !municipio.isEmpty()) {
            specs = specs.and((root, query, builder) -> 
                builder.like(builder.lower(root.get("municipio")), "%" + municipio.toLowerCase() + "%"));
        }
        if (numeroPersonas != null) {
            specs = specs.and((root, query, builder) -> 
                builder.greaterThanOrEqualTo(root.get("numeroPersonas"), numeroPersonas));
        }
        
        List<Propiedad> propiedades = propiedadRepository.findAll(specs);
        return propiedades.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    private PropiedadDTO convertToDTO(Propiedad propiedad) {
        PropiedadDTO dto = new PropiedadDTO();
        dto.setId(propiedad.getId());
        dto.setDireccion(propiedad.getDireccion() != null ? propiedad.getDireccion() : "Desconocido");
        dto.setDescripcion(propiedad.getDescripcion() != null ? propiedad.getDescripcion() : "Sin descripción");
        dto.setPrecio(propiedad.getPrecio() != null ? propiedad.getPrecio() : 0.0);
    
        // Asegurarse de que el campo usuario no sea nulo antes de acceder a él
        if (propiedad.getUsuario() != null) {
            dto.setUsuarioId(propiedad.getUsuario().getId());
        }
    
        // Asignar valores no nulos para los campos adicionales
        dto.setMunicipio(propiedad.getMunicipio() != null ? propiedad.getMunicipio() : "Sin municipio");
        dto.setNumeroPersonas(propiedad.getNumeroPersonas() != null ? propiedad.getNumeroPersonas() : 0);
        dto.setEstado(propiedad.getEstado() != null ? propiedad.getEstado() : "Desconocido");
        dto.setNombre(propiedad.getNombre() != null ? propiedad.getNombre() : "Sin nombre");
    
        return dto;
    }
}