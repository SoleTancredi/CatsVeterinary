package com.veterinaria.exclusive_cats.service.mapper;

import com.veterinaria.exclusive_cats.controller.dto.VeterinarioDto;
import com.veterinaria.exclusive_cats.entity.Veterinario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VeterinarioMapper {
    VeterinarioDto toDto(Veterinario veterinario);
    Veterinario toEntity(VeterinarioDto veterinarioDto);
}
