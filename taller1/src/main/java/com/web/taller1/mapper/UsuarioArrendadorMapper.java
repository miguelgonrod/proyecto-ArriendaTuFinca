package com.web.taller1.mapper;

import org.mapstruct.Mapper;

import com.web.taller1.DTO.UsuarioArrendadorDTO;
import com.web.taller1.entities.UsuarioArrendador;

@Mapper(componentModel = "spring")
public interface UsuarioArrendadorMapper {

    // Convertir entidad a DTO
    UsuarioArrendadorDTO toDTO(UsuarioArrendador arrendador);

    // Convertir DTO a entidad
    UsuarioArrendador toEntity(UsuarioArrendadorDTO arrendadorDTO);
}
