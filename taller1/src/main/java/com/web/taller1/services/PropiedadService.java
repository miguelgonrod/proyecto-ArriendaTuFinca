package com.web.taller1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import com.web.taller1.repositories.PropiedadRepository;
import com.web.taller1.repositories.UsuarioArrendatarioRepository;
import com.web.taller1.entities.Propiedad;
import com.web.taller1.entities.UsuarioArrendatario;
import com.web.taller1.DTO.PropiedadDTO;


@Service
public class PropiedadService {

    @Autowired
    private PropiedadRepository propiedadRepository;

    @Autowired
    private UsuarioArrendatarioRepository usuarioArrendatarioRepository;

    // Obtener propiedades por arrendatario
    public List<PropiedadDTO> getPropiedadesByArrendatario(Long arrendatarioId) {
        List<Propiedad> propiedades = propiedadRepository.findByArrendatarioId(arrendatarioId);
        return propiedades.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    // Crear una nueva propiedad
    public PropiedadDTO createPropiedad(PropiedadDTO propiedadDTO) {
        // Buscar el arrendatario por su ID
        UsuarioArrendatario arrendatario = usuarioArrendatarioRepository.findById(propiedadDTO.getArrendatarioId())
                .orElseThrow(() -> new RuntimeException("Arrendatario no encontrado con id: " + propiedadDTO.getArrendatarioId()));

        // Crear la entidad Propiedad y asignar el arrendatario
        Propiedad propiedad = new Propiedad();
        propiedad.setDireccion(propiedadDTO.getDireccion());
        propiedad.setDescripcion(propiedadDTO.getDescripcion());
        propiedad.setPrecio(propiedadDTO.getPrecio());
        propiedad.setArrendatario(arrendatario);  // Asignar el arrendatario a la propiedad

        // Guardar la nueva propiedad en la base de datos
        Propiedad nuevaPropiedad = propiedadRepository.save(propiedad);

        // Convertir la entidad guardada a DTO y devolverla
        return convertToDTO(nuevaPropiedad);
    }

    // Obtener propiedad por ID
    public PropiedadDTO getPropiedadById(Long id) {
        Propiedad propiedad = propiedadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propiedad no encontrada con id: " + id));
        return convertToDTO(propiedad);
    }

    // Actualizar propiedad
    public PropiedadDTO updatePropiedad(Long id, PropiedadDTO propiedadDTO) {
        Propiedad propiedad = propiedadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propiedad no encontrada con id: " + id));

        // Actualiza los campos de la propiedad
        propiedad.setDireccion(propiedadDTO.getDireccion());
        propiedad.setDescripcion(propiedadDTO.getDescripcion());
        propiedad.setPrecio(propiedadDTO.getPrecio());

        // Actualiza el arrendatario si es necesario
        if (propiedadDTO.getArrendatarioId() != null) {
            UsuarioArrendatario arrendatario = new UsuarioArrendatario(); // Busca el arrendatario en tu DB si es necesario
            arrendatario.setId(propiedadDTO.getArrendatarioId());
            propiedad.setArrendatario(arrendatario);
        }

        // Guardar los cambios en la base de datos
        Propiedad propiedadActualizada = propiedadRepository.save(propiedad);
        return convertToDTO(propiedadActualizada);
    }

    // Eliminar propiedad
    public void deletePropiedad(Long id) {
        Propiedad propiedad = propiedadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propiedad no encontrada con id: " + id));

        // Eliminar la propiedad de la base de datos
        propiedadRepository.delete(propiedad);
    }

    // Convertir entidad a DTO
    private PropiedadDTO convertToDTO(Propiedad propiedad) {
        PropiedadDTO dto = new PropiedadDTO();
        dto.setId(propiedad.getId());
        dto.setDireccion(propiedad.getDireccion());
        dto.setDescripcion(propiedad.getDescripcion());
        dto.setPrecio(propiedad.getPrecio());
        dto.setArrendatarioId(propiedad.getArrendatario().getId());
        return dto;
    }

    // Convertir DTO a entidad
    private Propiedad convertToEntity(PropiedadDTO dto) {
        Propiedad propiedad = new Propiedad();
        propiedad.setDireccion(dto.getDireccion());
        propiedad.setDescripcion(dto.getDescripcion());
        propiedad.setPrecio(dto.getPrecio());
        // Aquí también deberás buscar el `UsuarioArrendatario` por su ID si es necesario
        return propiedad;
    }
    
    
}
