package com.veterinaria.exclusive_cats.service.mapper;

import com.veterinaria.exclusive_cats.controller.dto.DuenioDto;
import com.veterinaria.exclusive_cats.entity.Duenio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DuenioMapper {
    DuenioDto toDto(Duenio duenio);
    Duenio toEntity(DuenioDto duenioDto);
}
