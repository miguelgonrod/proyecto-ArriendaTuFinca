package com.web.taller1.mapper;

import com.web.taller1.DTO.SolicitudArrendamientoDTO;
import com.web.taller1.DTO.UsuarioArrendadorDTO;
import com.web.taller1.entities.SolicitudArrendamiento;
import com.web.taller1.entities.UsuarioArrendador;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-05T16:34:45-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.4 (Ubuntu)"
)
@Component
public class UsuarioArrendadorMapperImpl implements UsuarioArrendadorMapper {

    @Override
    public UsuarioArrendadorDTO toDTO(UsuarioArrendador arrendador) {
        if ( arrendador == null ) {
            return null;
        }

        UsuarioArrendadorDTO usuarioArrendadorDTO = new UsuarioArrendadorDTO();

        usuarioArrendadorDTO.setId( arrendador.getId() );
        usuarioArrendadorDTO.setNombre( arrendador.getNombre() );
        usuarioArrendadorDTO.setEmail( arrendador.getEmail() );
        usuarioArrendadorDTO.setPassword( arrendador.getPassword() );
        usuarioArrendadorDTO.setSolicitudes( solicitudArrendamientoListToSolicitudArrendamientoDTOList( arrendador.getSolicitudes() ) );

        return usuarioArrendadorDTO;
    }

    @Override
    public UsuarioArrendador toEntity(UsuarioArrendadorDTO arrendadorDTO) {
        if ( arrendadorDTO == null ) {
            return null;
        }

        UsuarioArrendador usuarioArrendador = new UsuarioArrendador();

        usuarioArrendador.setId( arrendadorDTO.getId() );
        usuarioArrendador.setNombre( arrendadorDTO.getNombre() );
        usuarioArrendador.setEmail( arrendadorDTO.getEmail() );
        usuarioArrendador.setPassword( arrendadorDTO.getPassword() );
        usuarioArrendador.setSolicitudes( solicitudArrendamientoDTOListToSolicitudArrendamientoList( arrendadorDTO.getSolicitudes() ) );

        return usuarioArrendador;
    }

    protected SolicitudArrendamientoDTO solicitudArrendamientoToSolicitudArrendamientoDTO(SolicitudArrendamiento solicitudArrendamiento) {
        if ( solicitudArrendamiento == null ) {
            return null;
        }

        SolicitudArrendamientoDTO solicitudArrendamientoDTO = new SolicitudArrendamientoDTO();

        solicitudArrendamientoDTO.setId( solicitudArrendamiento.getId() );
        solicitudArrendamientoDTO.setEstado( solicitudArrendamiento.getEstado() );
        solicitudArrendamientoDTO.setFechaSolicitud( solicitudArrendamiento.getFechaSolicitud() );

        return solicitudArrendamientoDTO;
    }

    protected List<SolicitudArrendamientoDTO> solicitudArrendamientoListToSolicitudArrendamientoDTOList(List<SolicitudArrendamiento> list) {
        if ( list == null ) {
            return null;
        }

        List<SolicitudArrendamientoDTO> list1 = new ArrayList<SolicitudArrendamientoDTO>( list.size() );
        for ( SolicitudArrendamiento solicitudArrendamiento : list ) {
            list1.add( solicitudArrendamientoToSolicitudArrendamientoDTO( solicitudArrendamiento ) );
        }

        return list1;
    }

    protected SolicitudArrendamiento solicitudArrendamientoDTOToSolicitudArrendamiento(SolicitudArrendamientoDTO solicitudArrendamientoDTO) {
        if ( solicitudArrendamientoDTO == null ) {
            return null;
        }

        SolicitudArrendamiento solicitudArrendamiento = new SolicitudArrendamiento();

        solicitudArrendamiento.setId( solicitudArrendamientoDTO.getId() );
        solicitudArrendamiento.setEstado( solicitudArrendamientoDTO.getEstado() );
        solicitudArrendamiento.setFechaSolicitud( solicitudArrendamientoDTO.getFechaSolicitud() );

        return solicitudArrendamiento;
    }

    protected List<SolicitudArrendamiento> solicitudArrendamientoDTOListToSolicitudArrendamientoList(List<SolicitudArrendamientoDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<SolicitudArrendamiento> list1 = new ArrayList<SolicitudArrendamiento>( list.size() );
        for ( SolicitudArrendamientoDTO solicitudArrendamientoDTO : list ) {
            list1.add( solicitudArrendamientoDTOToSolicitudArrendamiento( solicitudArrendamientoDTO ) );
        }

        return list1;
    }
}
