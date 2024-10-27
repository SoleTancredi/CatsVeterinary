package com.veterinaria.exclusive_cats.service;

import com.veterinaria.exclusive_cats.controller.dto.ConsultaDto;
import java.util.List;

public interface ConsultaService {
    public ConsultaDto createConsulta(ConsultaDto consultaDto);
    public List<ConsultaDto> getConsultas();
    public ConsultaDto getConsultaById(Long id);
    public void deleteConsulta(Long id);
    public ConsultaDto updateConsulta(ConsultaDto consultaDto);
}
