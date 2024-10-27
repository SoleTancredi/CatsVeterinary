package com.veterinaria.exclusive_cats.service;

import com.veterinaria.exclusive_cats.controller.dto.GatoDto;

import java.util.List;

public interface GatoService {

    public GatoDto createGato(GatoDto gatoDto);

    public List<GatoDto> getGatos();
    public GatoDto getGatoById(Long id);
    public void deleteGato(Long id);
    public GatoDto updateGato(GatoDto gatoDto);

}
