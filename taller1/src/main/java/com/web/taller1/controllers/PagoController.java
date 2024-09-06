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
import com.web.taller1.services.PagoService;
import com.web.taller1.DTO.PagoDTO;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PostMapping
    public ResponseEntity<PagoDTO> createPago(@RequestBody PagoDTO pagoDTO) {
        PagoDTO nuevoPago = pagoService.createPago(pagoDTO);
        return new ResponseEntity<>(nuevoPago, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PagoDTO>> getAllPagos() {
        List<PagoDTO> pagos = pagoService.getAllPagos();
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> getPagoById(@PathVariable Long id) {
        PagoDTO pago = pagoService.getPagoById(id);
        return new ResponseEntity<>(pago, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoDTO> updatePago(@PathVariable Long id, @RequestBody PagoDTO pagoDTO) {
        PagoDTO pagoActualizado = pagoService.updatePago(id, pagoDTO);
        return new ResponseEntity<>(pagoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePago(@PathVariable Long id) {
        pagoService.deletePago(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
