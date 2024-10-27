package com.veterinaria.exclusive_cats.service;

import com.veterinaria.exclusive_cats.controller.dto.DuenioDto;

import java.util.List;

public interface DuenioService {
    public DuenioDto createDuenio(DuenioDto duenioDto);

    public List<DuenioDto> getDuenios();
    public DuenioDto getDuenioById(Long id);
    public void deleteDuenio(Long id);
    public DuenioDto updateDuenio(DuenioDto duenioDto);
}
