package com.web.taller1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.web.taller1.repositories.CalificacionRepository;
import com.web.taller1.repositories.PropiedadRepository;
import com.web.taller1.repositories.UsuarioRepository;
import com.web.taller1.DTO.CalificacionDTO;
import com.web.taller1.entities.Calificacion;
import com.web.taller1.entities.Propiedad;
import com.web.taller1.entities.Usuario;

@Service
public class CalificacionService {

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PropiedadRepository propiedadRepository;

    public CalificacionDTO createCalificacion(CalificacionDTO calificacionDTO) {
        Calificacion calificacion = new Calificacion();

        // Verificar si la puntuación no es nula antes de asignarla
        if (calificacionDTO.getPuntuacion() != null) {
            calificacion.setPuntuacion(calificacionDTO.getPuntuacion());
        } else {
            throw new IllegalArgumentException("La puntuación no puede ser nula");
        }

        // Asignar otros campos, como comentario, usuario y propiedad
        calificacion.setComentario(calificacionDTO.getComentario());

        Usuario usuario = usuarioRepository.findById(calificacionDTO.getUsuarioId())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + calificacionDTO.getUsuarioId()));
        calificacion.setUsuario(usuario);

        Propiedad propiedad = propiedadRepository.findById(calificacionDTO.getPropiedadId())
            .orElseThrow(() -> new RuntimeException("Propiedad no encontrada con id: " + calificacionDTO.getPropiedadId()));
        calificacion.setPropiedad(propiedad);

        calificacionRepository.save(calificacion);

        return convertToDTO(calificacion);
    }

    public List<CalificacionDTO> getAllCalificaciones() {
        List<Calificacion> calificaciones = calificacionRepository.findAll();
        return calificaciones.stream().map(CalificacionDTO::new).collect(Collectors.toList());
    }

    public CalificacionDTO getCalificacionById(Long id) {
        Calificacion calificacion = calificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Calificación no encontrada con id: " + id));
        return new CalificacionDTO(calificacion);
    }

    public CalificacionDTO updateCalificacion(Long id, CalificacionDTO calificacionDTO) {
        Calificacion calificacion = calificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Calificación no encontrada con id: " + id));
        calificacion.setPuntuacion(calificacionDTO.getPuntuacion());
        calificacion.setComentario(calificacionDTO.getComentario());
        Calificacion calificacionActualizada = calificacionRepository.save(calificacion);
        return new CalificacionDTO(calificacionActualizada);
    }

    public void deleteCalificacion(Long id) {
        Calificacion calificacion = calificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Calificación no encontrada con id: " + id));
        calificacionRepository.delete(calificacion);
    }
    public CalificacionDTO convertToDTO(Calificacion calificacion) {
        CalificacionDTO dto = new CalificacionDTO();
        dto.setId(calificacion.getId());
        dto.setPuntuacion(calificacion.getPuntuacion());
        dto.setComentario(calificacion.getComentario());

        if (calificacion.getUsuario() != null) {
            dto.setUsuarioId(calificacion.getUsuario().getId());
        }

        if (calificacion.getPropiedad() != null) {
            dto.setPropiedadId(calificacion.getPropiedad().getId());
        }

        return dto;
    }
}
