package com.web.taller1.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.web.taller1.repositories.PropiedadRepository;
import com.web.taller1.repositories.SolicitudArrendamientoRepository;
import com.web.taller1.repositories.UsuarioRepository;
import com.web.taller1.DTO.SolicitudArrendamientoDTO;
import com.web.taller1.entities.Propiedad;
import com.web.taller1.entities.SolicitudArrendamiento;
import com.web.taller1.entities.Usuario;


@Service
public class SolicitudArrendamientoService {

    @Autowired
    private SolicitudArrendamientoRepository solicitudRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PropiedadRepository propiedadRepository;

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public SolicitudArrendamientoDTO createSolicitud(SolicitudArrendamientoDTO solicitudDTO) {
        SolicitudArrendamiento solicitud = new SolicitudArrendamiento();

        try {
            // Convertimos el String a Date antes de asignarlo
            solicitud.setFechaSolicitud(formatter.parse(solicitudDTO.getFechaSolicitud()));
            solicitud.setFechaEntrada(formatter.parse(solicitudDTO.getFechaEntrada()));
            solicitud.setFechaSalida(formatter.parse(solicitudDTO.getFechaSalida()));
        } catch (ParseException e) {
            throw new RuntimeException("Error al parsear la fecha: " + e.getMessage());
        }

        solicitud.setEstado(solicitudDTO.getEstado());

        Usuario usuario = usuarioRepository.findById(solicitudDTO.getUsuarioId())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + solicitudDTO.getUsuarioId()));
        solicitud.setArrendador(usuario);

        Propiedad propiedad = propiedadRepository.findById(solicitudDTO.getPropiedadId())
            .orElseThrow(() -> new RuntimeException("Propiedad no encontrada con id: " + solicitudDTO.getPropiedadId()));
        solicitud.setPropiedad(propiedad);

        solicitudRepository.save(solicitud);

        return convertToDTO(solicitud);
    }

    public SolicitudArrendamientoDTO updateSolicitud(Long id, SolicitudArrendamientoDTO solicitudDTO) {
        Optional<SolicitudArrendamiento> solicitudOpt = solicitudRepository.findById(id);
        
        if (solicitudOpt.isPresent()) {
            SolicitudArrendamiento solicitud = solicitudOpt.get();
            
            // Actualizamos los campos con los valores del DTO
            solicitud.setEstado(solicitudDTO.getEstado());
            try {
                // Convertimos el String a Date
                solicitud.setFechaSolicitud(formatter.parse(solicitudDTO.getFechaSolicitud()));
                solicitud.setFechaEntrada(formatter.parse(solicitudDTO.getFechaEntrada()));
                solicitud.setFechaSalida(formatter.parse(solicitudDTO.getFechaSalida()));
            } catch (ParseException e) {
                throw new RuntimeException("Error al parsear la fecha: " + e.getMessage());
            }

            solicitudRepository.save(solicitud);
            return convertToDTO(solicitud);
        } else {
            throw new RuntimeException("Solicitud de arrendamiento no encontrada con id: " + id);
        }
    }

    public SolicitudArrendamientoDTO getSolicitudById(Long id) {
        Optional<SolicitudArrendamiento> solicitudOpt = solicitudRepository.findById(id);
        if (solicitudOpt.isPresent()) {
            return convertToDTO(solicitudOpt.get());
        } else {
            throw new RuntimeException("Solicitud de arrendamiento no encontrada con id: " + id);
        }
    }

    public void deleteSolicitud(Long id) {
        if (solicitudRepository.existsById(id)) {
            solicitudRepository.deleteById(id);
        } else {
            throw new RuntimeException("Solicitud de arrendamiento no encontrada con id: " + id);
        }
    }

    public List<SolicitudArrendamientoDTO> getAllSolicitudes() {
        List<SolicitudArrendamiento> solicitudes = solicitudRepository.findAll();
        return solicitudes.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    private SolicitudArrendamientoDTO convertToDTO(SolicitudArrendamiento solicitud) {
        SolicitudArrendamientoDTO dto = new SolicitudArrendamientoDTO();
        dto.setId(solicitud.getId());
        dto.setEstado(solicitud.getEstado());

        // Convertimos el Date a String antes de enviarlo en el DTO
        dto.setFechaSolicitud(formatter.format(solicitud.getFechaSolicitud()));
        dto.setFechaEntrada(formatter.format(solicitud.getFechaEntrada()));
        dto.setFechaSalida(formatter.format(solicitud.getFechaSalida()));

        dto.setUsuarioId(solicitud.getArrendador().getId());
        dto.setPropiedadId(solicitud.getPropiedad().getId());

        return dto;
    }
}
