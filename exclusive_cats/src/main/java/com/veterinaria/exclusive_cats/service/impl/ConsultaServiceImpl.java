package com.veterinaria.exclusive_cats.service.impl;

import com.veterinaria.exclusive_cats.controller.dto.ConsultaDto;
import com.veterinaria.exclusive_cats.entity.Consulta;
import com.veterinaria.exclusive_cats.entity.Gato;
import com.veterinaria.exclusive_cats.entity.Veterinario;
import com.veterinaria.exclusive_cats.exceptions.ConsultaNotFoundException;
import com.veterinaria.exclusive_cats.exceptions.GatoNotFoundException;
import com.veterinaria.exclusive_cats.exceptions.InvalidInputException;
import com.veterinaria.exclusive_cats.repository.ConsultaRepository;
import com.veterinaria.exclusive_cats.repository.GatoRepository;
import com.veterinaria.exclusive_cats.repository.VeterinarioRepository;
import com.veterinaria.exclusive_cats.service.ConsultaService;
import com.veterinaria.exclusive_cats.service.mapper.ConsultaMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.veterinaria.exclusive_cats.entity.Consulta.crearConsulta;

@Service
public class ConsultaServiceImpl implements ConsultaService {
    private final GatoRepository gatoRepository;
    private final VeterinarioRepository veterinarioRepository;
    private final ConsultaMapper consultaMapper;
    private final ConsultaRepository consultaRepository;

    public ConsultaServiceImpl(GatoRepository gatoRepository, VeterinarioRepository veterinarioRepository, ConsultaMapper consultaMapper, ConsultaRepository consultaRepository) {
        this.gatoRepository = gatoRepository;
        this.veterinarioRepository = veterinarioRepository;
        this.consultaMapper = consultaMapper;
        this.consultaRepository = consultaRepository;
    }

    @Override
    @Transactional
    public ConsultaDto createConsulta(ConsultaDto consultaDto) {
        if (consultaDto == null) {
            throw new InvalidInputException("El gatoDto no puede ser nulo.");
        }

        Gato gato = gatoRepository.findById(consultaDto.getGatoId())
                .orElseThrow(() -> new GatoNotFoundException("No se encontró el gato con ID: " + consultaDto.getGatoId()));

        Veterinario veterinario = veterinarioRepository.findById(consultaDto.getVeterinarioId())
                .orElseThrow(() -> new GatoNotFoundException("No se encontró el veterinario con ID: " + consultaDto.getVeterinarioId()));

        System.out.println("ID DEL GATOTE QUE ESTOY PASANDO AL QUERER CREAR CONSULTA ----------" + gato.toString());
        System.out.println("ID VETERINARIOOOOO QUE ESTOY PASANDO AL QUERER CREAR CONSULTA ----------" + veterinario.toString());
        Consulta consulta = crearConsulta(veterinario, gato, consultaDto.getFecha(), consultaDto.getMotivoConsulta());

        Consulta savedConsulta = consultaRepository.save(consulta);

        return consultaMapper.toDto(savedConsulta);
    }

    @Override
    public List<ConsultaDto> getConsultas() {
        List<Consulta> listConsulta = consultaRepository.findAll();

        return listConsulta.stream()
                .map(consultaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ConsultaDto getConsultaById(Long id) {
        return consultaRepository.findById(id)
                .map(consultaMapper::toDto)
                .orElseThrow(() -> new ConsultaNotFoundException("Consulta no encontrada! " + id));
    }

    @Override
    @Transactional
    public void deleteConsulta(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new ConsultaNotFoundException("Consulta no encontrada!"));

        consultaRepository.delete(consulta);
    }

    @Override
    @Transactional
    public ConsultaDto updateConsulta(ConsultaDto consultaDto) {
        Consulta consulta = consultaRepository.findById(consultaDto.getId())
                .orElseThrow(() -> new GatoNotFoundException("Consulta no encontrada!"));

        boolean cambios = false;

        if (updateFieldIfDifferent(consulta::getFecha, consulta::setFecha, consultaDto.getFecha())) {
            cambios = true;
        }
        if (updateFieldIfDifferent(consulta::getMotivoConsulta, consulta::setMotivoConsulta, consultaDto.getMotivoConsulta())) {
            cambios = true;
        }

        if (cambios) {
            consultaRepository.save(consulta);
        }

        return consultaMapper.toDto(consulta);
    }

    private <T> boolean updateFieldIfDifferent(Supplier<T> getter, Consumer<T> setter, T newValue) {
        if (!Objects.equals(getter.get(), newValue)) {
            setter.accept(newValue);
            return true;
        }
        return false;
    }
}
