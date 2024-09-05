package com.web.taller1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.web.taller1.repositories.SolicitudArrendamientoRepository;
import com.web.taller1.DTO.SolicitudArrendamientoDTO;
import com.web.taller1.entities.SolicitudArrendamiento;
import com.web.taller1.mapper.SolicitudArrendamientoMapper;


@Service
public class SolicitudArrendamientoService {

    @Autowired
    private SolicitudArrendamientoRepository solicitudRepository;

    @Autowired
    private SolicitudArrendamientoMapper solicitudMapper;

    // Crear una nueva solicitud
    public SolicitudArrendamientoDTO createSolicitud(SolicitudArrendamientoDTO solicitudDTO) {
        SolicitudArrendamiento solicitud = solicitudMapper.toEntity(solicitudDTO);
        SolicitudArrendamiento nuevaSolicitud = solicitudRepository.save(solicitud);
        return solicitudMapper.toDTO(nuevaSolicitud);
    }

    // Obtener todas las solicitudes
    public List<SolicitudArrendamientoDTO> getAllSolicitudes() {
        List<SolicitudArrendamiento> solicitudes = solicitudRepository.findAll();
        return solicitudes.stream().map(solicitudMapper::toDTO).collect(Collectors.toList());
    }

    // Obtener una solicitud por ID
    public SolicitudArrendamientoDTO getSolicitudById(Long id) {
        SolicitudArrendamiento solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con id: " + id));
        return solicitudMapper.toDTO(solicitud);
    }

    // Actualizar una solicitud
    public SolicitudArrendamientoDTO updateSolicitud(Long id, SolicitudArrendamientoDTO solicitudDTO) {
        SolicitudArrendamiento solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con id: " + id));

        solicitud.setEstado(solicitudDTO.getEstado());
        solicitud.setFechaSolicitud(solicitudDTO.getFechaSolicitud());

        SolicitudArrendamiento solicitudActualizada = solicitudRepository.save(solicitud);
        return solicitudMapper.toDTO(solicitudActualizada);
    }

    // Eliminar una solicitud
    public void deleteSolicitud(Long id) {
        SolicitudArrendamiento solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con id: " + id));
        solicitudRepository.delete(solicitud);
    }
}
