package com.web.taller1.mapper;

import com.web.taller1.DTO.PropiedadDTO;
import com.web.taller1.DTO.UsuarioArrendatarioDTO;
import com.web.taller1.entities.Propiedad;
import com.web.taller1.entities.UsuarioArrendatario;
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
public class UsuarioArrendatarioMapperImpl implements UsuarioArrendatarioMapper {

    @Override
    public UsuarioArrendatarioDTO toDTO(UsuarioArrendatario arrendatario) {
        if ( arrendatario == null ) {
            return null;
        }

        UsuarioArrendatarioDTO usuarioArrendatarioDTO = new UsuarioArrendatarioDTO();

        usuarioArrendatarioDTO.setId( arrendatario.getId() );
        usuarioArrendatarioDTO.setNombre( arrendatario.getNombre() );
        usuarioArrendatarioDTO.setEmail( arrendatario.getEmail() );
        usuarioArrendatarioDTO.setPassword( arrendatario.getPassword() );
        usuarioArrendatarioDTO.setPropiedades( propiedadListToPropiedadDTOList( arrendatario.getPropiedades() ) );

        return usuarioArrendatarioDTO;
    }

    @Override
    public UsuarioArrendatario toEntity(UsuarioArrendatarioDTO arrendatarioDTO) {
        if ( arrendatarioDTO == null ) {
            return null;
        }

        UsuarioArrendatario usuarioArrendatario = new UsuarioArrendatario();

        usuarioArrendatario.setId( arrendatarioDTO.getId() );
        usuarioArrendatario.setNombre( arrendatarioDTO.getNombre() );
        usuarioArrendatario.setEmail( arrendatarioDTO.getEmail() );
        usuarioArrendatario.setPassword( arrendatarioDTO.getPassword() );
        usuarioArrendatario.setPropiedades( propiedadDTOListToPropiedadList( arrendatarioDTO.getPropiedades() ) );

        return usuarioArrendatario;
    }

    protected PropiedadDTO propiedadToPropiedadDTO(Propiedad propiedad) {
        if ( propiedad == null ) {
            return null;
        }

        PropiedadDTO propiedadDTO = new PropiedadDTO();

        propiedadDTO.setId( propiedad.getId() );
        propiedadDTO.setDireccion( propiedad.getDireccion() );
        propiedadDTO.setDescripcion( propiedad.getDescripcion() );
        propiedadDTO.setPrecio( propiedad.getPrecio() );

        return propiedadDTO;
    }

    protected List<PropiedadDTO> propiedadListToPropiedadDTOList(List<Propiedad> list) {
        if ( list == null ) {
            return null;
        }

        List<PropiedadDTO> list1 = new ArrayList<PropiedadDTO>( list.size() );
        for ( Propiedad propiedad : list ) {
            list1.add( propiedadToPropiedadDTO( propiedad ) );
        }

        return list1;
    }

    protected Propiedad propiedadDTOToPropiedad(PropiedadDTO propiedadDTO) {
        if ( propiedadDTO == null ) {
            return null;
        }

        Propiedad propiedad = new Propiedad();

        propiedad.setId( propiedadDTO.getId() );
        propiedad.setDireccion( propiedadDTO.getDireccion() );
        propiedad.setDescripcion( propiedadDTO.getDescripcion() );
        propiedad.setPrecio( propiedadDTO.getPrecio() );

        return propiedad;
    }

    protected List<Propiedad> propiedadDTOListToPropiedadList(List<PropiedadDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Propiedad> list1 = new ArrayList<Propiedad>( list.size() );
        for ( PropiedadDTO propiedadDTO : list ) {
            list1.add( propiedadDTOToPropiedad( propiedadDTO ) );
        }

        return list1;
    }
}
