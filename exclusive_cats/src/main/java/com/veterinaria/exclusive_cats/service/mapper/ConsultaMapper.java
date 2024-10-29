package com.veterinaria.exclusive_cats.service.mapper;

import com.veterinaria.exclusive_cats.controller.dto.ConsultaDto;
import com.veterinaria.exclusive_cats.entity.Consulta;
import com.veterinaria.exclusive_cats.entity.Gato;
import com.veterinaria.exclusive_cats.entity.Veterinario;
import com.veterinaria.exclusive_cats.repository.GatoRepository;
import com.veterinaria.exclusive_cats.repository.VeterinarioRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ConsultaMapper {

    @Autowired
    private VeterinarioRepository veterinarioRepository;
    @Autowired
    private GatoRepository gatoRepository;

    @Mapping(source = "veterinarioId", target = "veterinarioId", qualifiedByName = "mapVeterinarioIdToEntity")
    @Mapping(source = "gatoId", target = "gatoId", qualifiedByName = "mapGatoIdToEntity")
    public abstract Consulta toEntity(ConsultaDto consultaDto);

    @Mapping(source = "veterinarioId.id", target = "veterinarioId")
    @Mapping(source = "gatoId.id", target = "gatoId")
    public abstract ConsultaDto toDto(Consulta consulta);

    @Named("mapVeterinarioIdToEntity")
    protected Veterinario mapVeterinarioIdToEntity(Long id) {
        return veterinarioRepository.findById(id).orElse(null);
    }

    @Named("mapGatoIdToEntity")
    protected Gato mapGatoIdToEntity(Long id) {
        return gatoRepository.findById(id).orElse(null);
    }

}

