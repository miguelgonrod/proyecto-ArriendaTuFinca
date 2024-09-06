package com.web.taller1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import com.web.taller1.repositories.PropiedadRepository;
import com.web.taller1.repositories.UsuarioRepository;
import com.web.taller1.entities.Propiedad;
import com.web.taller1.entities.Usuario;
import com.web.taller1.DTO.PropiedadDTO;


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

    public PropiedadDTO updatePropiedad(Long id, PropiedadDTO propiedadDTO) {
        Propiedad propiedad = propiedadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propiedad no encontrada con id: " + id));
        propiedad.setDireccion(propiedadDTO.getDireccion());
        propiedad.setDescripcion(propiedadDTO.getDescripcion());
        propiedad.setPrecio(propiedadDTO.getPrecio());
        propiedad.setMunicipio(propiedadDTO.getMunicipio());
        propiedad.setNumeroPersonas(propiedadDTO.getNumeroPersonas());
        Propiedad propiedadActualizada = propiedadRepository.save(propiedad);
        return new PropiedadDTO(propiedadActualizada);
    }

    public void deletePropiedad(Long id) {
        Propiedad propiedad = propiedadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propiedad no encontrada con id: " + id));
        propiedadRepository.delete(propiedad);
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
    
        return dto;
    }
}
