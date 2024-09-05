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

import com.web.taller1.services.UsuarioArrendatarioService;
import com.web.taller1.DTO.UsuarioArrendatarioDTO;

@RestController
@RequestMapping("/api/arrendatarios")
public class UsuarioArrendatarioController {

    @Autowired
    private UsuarioArrendatarioService usuarioArrendatarioService;

    // Crear un arrendatario
    @PostMapping
    public ResponseEntity<UsuarioArrendatarioDTO> createArrendatario(@RequestBody UsuarioArrendatarioDTO arrendatarioDTO) {
        UsuarioArrendatarioDTO nuevoArrendatario = usuarioArrendatarioService.createArrendatario(arrendatarioDTO);
        return new ResponseEntity<>(nuevoArrendatario, HttpStatus.CREATED);
    }

    // Obtener todos los arrendatarios
    @GetMapping
    public ResponseEntity<List<UsuarioArrendatarioDTO>> getAllArrendatarios() {
        List<UsuarioArrendatarioDTO> arrendatarios = usuarioArrendatarioService.getAllArrendatarios();
        return new ResponseEntity<>(arrendatarios, HttpStatus.OK);
    }

    // Obtener un arrendatario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioArrendatarioDTO> getArrendatarioById(@PathVariable Long id) {
        UsuarioArrendatarioDTO arrendatario = usuarioArrendatarioService.getArrendatarioById(id);
        return new ResponseEntity<>(arrendatario, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioArrendatarioDTO> updateArrendatario(
            @PathVariable Long id, @RequestBody UsuarioArrendatarioDTO arrendatarioDTO) {
        UsuarioArrendatarioDTO arrendatarioActualizado = usuarioArrendatarioService.updateArrendatario(id, arrendatarioDTO);
        return new ResponseEntity<>(arrendatarioActualizado, HttpStatus.OK);
    }

        // Método para eliminar un arrendatario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArrendatario(@PathVariable Long id) {
        usuarioArrendatarioService.deleteArrendatario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Devolver 204 No Content al eliminar
    }
}
