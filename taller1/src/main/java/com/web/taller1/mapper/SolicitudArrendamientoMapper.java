package com.web.taller1.mapper;

import org.mapstruct.Mapper;

import com.web.taller1.DTO.SolicitudArrendamientoDTO;
import com.web.taller1.entities.SolicitudArrendamiento;

@Mapper(componentModel = "spring")
public interface SolicitudArrendamientoMapper {

    // Convertir entidad a DTO
    SolicitudArrendamientoDTO toDTO(SolicitudArrendamiento solicitud);

    // Convertir DTO a entidad
    SolicitudArrendamiento toEntity(SolicitudArrendamientoDTO solicitudDTO);
}
