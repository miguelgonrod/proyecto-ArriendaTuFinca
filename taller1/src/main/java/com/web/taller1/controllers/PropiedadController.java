package com.web.taller1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.web.taller1.DTO.PropiedadDTO;
import com.web.taller1.services.PropiedadService;

@RestController
@RequestMapping("/api/propiedades")
@CrossOrigin(origins = "http://localhost:4200")
public class PropiedadController {

    @Autowired
    private PropiedadService propiedadService;

    @PostMapping
    public ResponseEntity<PropiedadDTO> createPropiedad(@RequestBody PropiedadDTO propiedadDTO) {
        PropiedadDTO nuevaPropiedad = propiedadService.createPropiedad(propiedadDTO);
        return new ResponseEntity<>(nuevaPropiedad, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PropiedadDTO>> getAllPropiedades() {
        List<PropiedadDTO> propiedades = propiedadService.getAllPropiedades();
        return new ResponseEntity<>(propiedades, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropiedadDTO> getPropiedadById(@PathVariable Long id) {
        PropiedadDTO propiedad = propiedadService.getPropiedadById(id);
        return new ResponseEntity<>(propiedad, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropiedadDTO> updatePropiedad(@PathVariable Long id, @RequestBody PropiedadDTO propiedadDTO) {
        PropiedadDTO propiedadActualizada = propiedadService.updatePropiedad(id, propiedadDTO);
        return new ResponseEntity<>(propiedadActualizada, HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PropiedadDTO>> getPropiedadesByUsuarioId(@PathVariable Long usuarioId) {
        List<PropiedadDTO> propiedades = propiedadService.getPropiedadesByUsuarioId(usuarioId);
        return new ResponseEntity<>(propiedades, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePropiedad(@PathVariable Long id) {
        propiedadService.deletePropiedad(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // PropiedadController.java
    @GetMapping("/buscar")
    public ResponseEntity<List<PropiedadDTO>> buscarPropiedades(
        @RequestParam(required = false) String nombre,
        @RequestParam(required = false) String municipio,
        @RequestParam(required = false) Integer numeroPersonas
    ) {
        List<PropiedadDTO> propiedades = propiedadService.buscarPropiedades(nombre, municipio, numeroPersonas);
        return new ResponseEntity<>(propiedades, HttpStatus.OK);
    }

    
}