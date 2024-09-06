package com.web.taller1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.taller1.repositories.PagoRepository;
import com.web.taller1.repositories.PropiedadRepository;
import com.web.taller1.repositories.UsuarioRepository;

import java.util.stream.Collectors;


import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.web.taller1.DTO.PagoDTO;
import com.web.taller1.entities.Pago;
import com.web.taller1.entities.Propiedad;
import com.web.taller1.entities.Usuario;

@Getter
@Setter
@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PropiedadRepository propiedadRepository;

    // Método para obtener todos los pagos
    public List<PagoDTO> getAllPagos() {
        List<Pago> pagos = pagoRepository.findAll();
        return pagos.stream()
                    .map(PagoDTO::new)  // Convertimos cada Pago en un PagoDTO
                    .collect(Collectors.toList());
    }

    // Otros métodos CRUD como create, update, delete...

    public PagoDTO createPago(PagoDTO pagoDTO) {
        if (pagoDTO.getMonto() == null) {
            throw new IllegalArgumentException("El monto no puede ser nulo");
        }
        if (pagoDTO.getFechaPago() == null) {
            throw new IllegalArgumentException("La fecha de pago no puede ser nula");
        }
        if (pagoDTO.getPropiedadId() == null) {
            throw new IllegalArgumentException("El ID de la propiedad no puede ser nulo");
        }

        Usuario usuario = usuarioRepository.findById(pagoDTO.getUsuarioId())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + pagoDTO.getUsuarioId()));

        Propiedad propiedad = propiedadRepository.findById(pagoDTO.getPropiedadId())
            .orElseThrow(() -> new RuntimeException("Propiedad no encontrada con id: " + pagoDTO.getPropiedadId()));

        Pago pago = new Pago();
        pago.setMonto(pagoDTO.getMonto());
        pago.setFechaPago(pagoDTO.getFechaPago());
        pago.setUsuario(usuario);
        pago.setPropiedad(propiedad);

        pagoRepository.save(pago);

        return convertToDTO(pago);
    }
    

    public PagoDTO getPagoById(Long id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con id: " + id));
        return new PagoDTO(pago);
    }

    public PagoDTO updatePago(Long id, PagoDTO pagoDTO) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con id: " + id));
        pago.setMonto(pagoDTO.getMonto());
        pago.setFechaPago(pagoDTO.getFechaPago());
        Pago pagoActualizado = pagoRepository.save(pago);
        return new PagoDTO(pagoActualizado);
    }

    public void deletePago(Long id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con id: " + id));
        pagoRepository.delete(pago);
    }

    public PagoDTO convertToDTO(Pago pago) {
        PagoDTO dto = new PagoDTO();
        dto.setId(pago.getId());
        
        // Verifica si el monto no es nulo antes de asignarlo
        if (pago.getMonto() != null) {
            dto.setMonto(pago.getMonto());
        }
        
        // Verifica si la fecha de pago no es nula antes de asignarla
        if (pago.getFechaPago() != null) {
            dto.setFechaPago(pago.getFechaPago());
        }
        
        // Verifica si el usuario no es nulo antes de asignar su ID
        if (pago.getUsuario() != null) {
            dto.setUsuarioId(pago.getUsuario().getId());
        }
    
        // Verifica si la propiedad no es nula antes de asignar su ID
        if (pago.getPropiedad() != null) {
            dto.setPropiedadId(pago.getPropiedad().getId());
        }
        
        return dto;
    }
    
}
