package com.veterinaria.exclusive_cats.service;

import com.veterinaria.exclusive_cats.controller.dto.VeterinarioDto;

import java.util.List;

public interface VeterinarioService {
    public VeterinarioDto createVeterinario(VeterinarioDto veterinarioDto);

    public List<VeterinarioDto> getVeterinarios();
    public VeterinarioDto getVeterinarioById(Long id);
    public void delete(Long id);
    public VeterinarioDto updateVeterinario(VeterinarioDto veterinarioDto);
}
