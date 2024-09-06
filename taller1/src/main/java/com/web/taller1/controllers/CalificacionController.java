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
import com.web.taller1.services.CalificacionService;
import com.web.taller1.DTO.CalificacionDTO;

@RestController
@RequestMapping("/api/calificaciones")
public class CalificacionController {

    @Autowired
    private CalificacionService calificacionService;

    @PostMapping
    public ResponseEntity<CalificacionDTO> createCalificacion(@RequestBody CalificacionDTO calificacionDTO) {
        CalificacionDTO nuevaCalificacion = calificacionService.createCalificacion(calificacionDTO);
        return new ResponseEntity<>(nuevaCalificacion, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CalificacionDTO>> getAllCalificaciones() {
        List<CalificacionDTO> calificaciones = calificacionService.getAllCalificaciones();
        return new ResponseEntity<>(calificaciones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalificacionDTO> getCalificacionById(@PathVariable Long id) {
        CalificacionDTO calificacion = calificacionService.getCalificacionById(id);
        return new ResponseEntity<>(calificacion, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalificacionDTO> updateCalificacion(@PathVariable Long id, @RequestBody CalificacionDTO calificacionDTO) {
        CalificacionDTO calificacionActualizada = calificacionService.updateCalificacion(id, calificacionDTO);
        return new ResponseEntity<>(calificacionActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalificacion(@PathVariable Long id) {
        calificacionService.deleteCalificacion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
