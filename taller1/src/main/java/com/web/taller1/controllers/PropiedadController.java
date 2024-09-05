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
import com.web.taller1.services.PropiedadService;
import com.web.taller1.DTO.PropiedadDTO;



@RestController
@RequestMapping("/api/propiedades")
public class PropiedadController {

    @Autowired
    private PropiedadService propiedadService;

    @GetMapping("/arrendatario/{arrendatarioId}")
    public List<PropiedadDTO> getPropiedadesByArrendatario(@PathVariable Long arrendatarioId) {
        return propiedadService.getPropiedadesByArrendatario(arrendatarioId);
    }

    // Otros endpoints para crear, actualizar, eliminar...
    @PostMapping
    public ResponseEntity<PropiedadDTO> createPropiedad(@RequestBody PropiedadDTO propiedadDTO) {
        PropiedadDTO nuevaPropiedad = propiedadService.createPropiedad(propiedadDTO);
        return new ResponseEntity<>(nuevaPropiedad, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropiedadDTO> getPropiedadById(@PathVariable Long id) {
        PropiedadDTO propiedadDTO = propiedadService.getPropiedadById(id);
        return new ResponseEntity<>(propiedadDTO, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PropiedadDTO> updatePropiedad(
            @PathVariable Long id, @RequestBody PropiedadDTO propiedadDTO) {
        PropiedadDTO propiedadActualizada = propiedadService.updatePropiedad(id, propiedadDTO);
        return new ResponseEntity<>(propiedadActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePropiedad(@PathVariable Long id) {
        propiedadService.deletePropiedad(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content si se elimina correctamente
    }
}
