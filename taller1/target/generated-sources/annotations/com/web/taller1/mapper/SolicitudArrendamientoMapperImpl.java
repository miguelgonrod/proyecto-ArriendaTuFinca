package com.web.taller1.mapper;

import com.web.taller1.DTO.SolicitudArrendamientoDTO;
import com.web.taller1.entities.SolicitudArrendamiento;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-05T16:34:45-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.4 (Ubuntu)"
)
@Component
public class SolicitudArrendamientoMapperImpl implements SolicitudArrendamientoMapper {

    @Override
    public SolicitudArrendamientoDTO toDTO(SolicitudArrendamiento solicitud) {
        if ( solicitud == null ) {
            return null;
        }

        SolicitudArrendamientoDTO solicitudArrendamientoDTO = new SolicitudArrendamientoDTO();

        solicitudArrendamientoDTO.setId( solicitud.getId() );
        solicitudArrendamientoDTO.setEstado( solicitud.getEstado() );
        solicitudArrendamientoDTO.setFechaSolicitud( solicitud.getFechaSolicitud() );

        return solicitudArrendamientoDTO;
    }

    @Override
    public SolicitudArrendamiento toEntity(SolicitudArrendamientoDTO solicitudDTO) {
        if ( solicitudDTO == null ) {
            return null;
        }

        SolicitudArrendamiento solicitudArrendamiento = new SolicitudArrendamiento();

        solicitudArrendamiento.setId( solicitudDTO.getId() );
        solicitudArrendamiento.setEstado( solicitudDTO.getEstado() );
        solicitudArrendamiento.setFechaSolicitud( solicitudDTO.getFechaSolicitud() );

        return solicitudArrendamiento;
    }
}
