package com.web.taller1.mapper;

import org.mapstruct.Mapper;

import com.web.taller1.DTO.UsuarioArrendatarioDTO;
import com.web.taller1.entities.UsuarioArrendatario;

@Mapper(componentModel = "spring")
public interface UsuarioArrendatarioMapper {

    UsuarioArrendatarioDTO toDTO(UsuarioArrendatario arrendatario);

    UsuarioArrendatario toEntity(UsuarioArrendatarioDTO arrendatarioDTO);
}
