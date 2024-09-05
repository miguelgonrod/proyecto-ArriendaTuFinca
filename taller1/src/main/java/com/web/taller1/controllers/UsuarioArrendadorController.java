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

import com.web.taller1.services.UsuarioArrendadorService;
import com.web.taller1.DTO.UsuarioArrendadorDTO;

@RestController
@RequestMapping("/api/arrendadores")
public class UsuarioArrendadorController {

    @Autowired
    private UsuarioArrendadorService arrendadorService;

    // Crear un arrendador
    @PostMapping
    public ResponseEntity<UsuarioArrendadorDTO> createArrendador(@RequestBody UsuarioArrendadorDTO arrendadorDTO) {
        UsuarioArrendadorDTO nuevoArrendador = arrendadorService.createArrendador(arrendadorDTO);
        return new ResponseEntity<>(nuevoArrendador, HttpStatus.CREATED);
    }

    // Obtener todos los arrendadores
    @GetMapping
    public ResponseEntity<List<UsuarioArrendadorDTO>> getAllArrendadores() {
        List<UsuarioArrendadorDTO> arrendadores = arrendadorService.getAllArrendadores();
        return new ResponseEntity<>(arrendadores, HttpStatus.OK);
    }

    // Obtener un arrendador por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioArrendadorDTO> getArrendadorById(@PathVariable Long id) {
        UsuarioArrendadorDTO arrendador = arrendadorService.getArrendadorById(id);
        return new ResponseEntity<>(arrendador, HttpStatus.OK);
    }

    // Actualizar un arrendador
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioArrendadorDTO> updateArrendador(
            @PathVariable Long id, @RequestBody UsuarioArrendadorDTO arrendadorDTO) {
        UsuarioArrendadorDTO arrendadorActualizado = arrendadorService.updateArrendador(id, arrendadorDTO);
        return new ResponseEntity<>(arrendadorActualizado, HttpStatus.OK);
    }

    // Eliminar un arrendador
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArrendador(@PathVariable Long id) {
        arrendadorService.deleteArrendador(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
