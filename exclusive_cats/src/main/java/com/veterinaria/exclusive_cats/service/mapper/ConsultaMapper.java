package com.veterinaria.exclusive_cats.service.mapper;

import com.veterinaria.exclusive_cats.controller.dto.ConsultaDto;
import com.veterinaria.exclusive_cats.entity.Consulta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsultaMapper {
    ConsultaDto toDto(Consulta consulta);
    Consulta toEntity(ConsultaDto consultaDto);
}
