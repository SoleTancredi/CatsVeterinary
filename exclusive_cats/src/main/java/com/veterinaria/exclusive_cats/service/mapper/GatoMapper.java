package com.veterinaria.exclusive_cats.service.mapper;

import com.veterinaria.exclusive_cats.controller.dto.GatoDto;
import com.veterinaria.exclusive_cats.entity.Gato;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GatoMapper {
    GatoDto toDto(Gato gato);
    Gato toEntity(GatoDto gatoDto);
}
