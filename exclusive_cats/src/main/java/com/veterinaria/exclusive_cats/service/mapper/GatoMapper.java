package com.veterinaria.exclusive_cats.service.mapper;

import com.veterinaria.exclusive_cats.controller.dto.GatoDto;
import com.veterinaria.exclusive_cats.entity.Duenio;
import com.veterinaria.exclusive_cats.entity.Gato;
import com.veterinaria.exclusive_cats.repository.DuenioRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class GatoMapper {

    @Autowired
    private DuenioRepository duenioRepository;

    @Mapping(source = "duenioId.id", target = "duenioId")
    public abstract GatoDto toDto(Gato gato);

    @Mapping(source = "duenioId", target = "duenioId.id")
    public abstract Gato toEntity(GatoDto gatoDto);

    @Named("mapDuenioIdToEntity")
    protected Duenio mapDuenioIdToEntity(Long id) {
        return duenioRepository.findById(id).orElse(null);
    }

}
