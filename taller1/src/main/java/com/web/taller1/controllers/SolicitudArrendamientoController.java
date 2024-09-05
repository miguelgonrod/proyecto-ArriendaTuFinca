package com.web.taller1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.web.taller1.services.SolicitudArrendamientoService;
import com.web.taller1.DTO.SolicitudArrendamientoDTO;


@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudArrendamientoController {

    @Autowired
    private SolicitudArrendamientoService solicitudService;

    // Crear una solicitud
    @PostMapping
    public ResponseEntity<SolicitudArrendamientoDTO> createSolicitud(@RequestBody SolicitudArrendamientoDTO solicitudDTO) {
        SolicitudArrendamientoDTO nuevaSolicitud = solicitudService.createSolicitud(solicitudDTO);
        return new ResponseEntity<>(nuevaSolicitud, HttpStatus.CREATED);
    }

    // Obtener todas las solicitudes
    @GetMapping
    public ResponseEntity<List<SolicitudArrendamientoDTO>> getAllSolicitudes() {
        List<SolicitudArrendamientoDTO> solicitudes = solicitudService.getAllSolicitudes();
        return new ResponseEntity<>(solicitudes, HttpStatus.OK);
    }

    // Obtener una solicitud por ID
    @GetMapping("/{id}")
    public ResponseEntity<SolicitudArrendamientoDTO> getSolicitudById(@PathVariable Long id) {
        SolicitudArrendamientoDTO solicitud = solicitudService.getSolicitudById(id);
        return new ResponseEntity<>(solicitud, HttpStatus.OK);
    }

    // Actualizar una solicitud
    @PutMapping("/{id}")
    public ResponseEntity<SolicitudArrendamientoDTO> updateSolicitud(
            @PathVariable Long id, @RequestBody SolicitudArrendamientoDTO solicitudDTO) {
        SolicitudArrendamientoDTO solicitudActualizada = solicitudService.updateSolicitud(id, solicitudDTO);
        return new ResponseEntity<>(solicitudActualizada, HttpStatus.OK);
    }

    // Eliminar una solicitud
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolicitud(@PathVariable Long id) {
        solicitudService.deleteSolicitud(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
