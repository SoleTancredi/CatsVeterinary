package com.veterinaria.exclusive_cats.service.impl;

import com.veterinaria.exclusive_cats.controller.dto.DuenioDto;
import com.veterinaria.exclusive_cats.entity.Duenio;
import com.veterinaria.exclusive_cats.exceptions.DuenioAlreadyExistException;
import com.veterinaria.exclusive_cats.exceptions.DuenioNotFoundException;
import com.veterinaria.exclusive_cats.exceptions.GatoNotFoundException;
import com.veterinaria.exclusive_cats.exceptions.InvalidInputException;
import com.veterinaria.exclusive_cats.repository.DuenioRepository;
import com.veterinaria.exclusive_cats.service.DuenioService;
import com.veterinaria.exclusive_cats.service.GatoService;
import com.veterinaria.exclusive_cats.service.mapper.DuenioMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.veterinaria.exclusive_cats.entity.Duenio.crearDuenio;
@Service
public class DuenioServiceImpl implements DuenioService {

    private final DuenioRepository duenioRepository;
    private final DuenioMapper duenioMapper;

    public DuenioServiceImpl(DuenioRepository duenioRepository, DuenioMapper duenioMapper) {
        this.duenioRepository = duenioRepository;
        this.duenioMapper = duenioMapper;
    }

    @Override
    @Transactional
    public DuenioDto createDuenio(DuenioDto duenioDto) {
        if (duenioDto == null) {
            throw new InvalidInputException("El duenioDto no puede ser nulo.");
        }

        duenioRepository.findByNombreAndApellido(duenioDto.getNombre(),duenioDto.getApellido()).ifPresent(existingDuenio -> {
            throw new DuenioAlreadyExistException(
                    "El duenio con nombre " + duenioDto.getNombre() + " y apellido " + duenioDto.getApellido() + " ya existe en la base de datos."
            );
        });

        Duenio duenio = crearDuenio(duenioDto.getNombre(), duenioDto.getApellido(), duenioDto.getDireccion(), duenioDto.getTelefono(), duenioDto.getEmail());
        Duenio savedDuenio = duenioRepository.save(duenio);

        return duenioMapper.toDto(duenio);
    }

    @Override
    public List<DuenioDto> getDuenios() {
        List<Duenio> listDuenios = duenioRepository.findAll();

        return listDuenios.stream()
                .map(duenioMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DuenioDto getDuenioById(Long id) {
        return duenioRepository.findById(id)
                .map(duenioMapper::toDto)
                .orElseThrow(() -> new GatoNotFoundException("Duenio no encontrado!"));
    }

    @Override
    @Transactional
    public void deleteDuenio(Long id) {
        Duenio duenio = duenioRepository.findById(id)
                .orElseThrow(() -> new DuenioNotFoundException("Duenio no encontrado!"));

        duenioRepository.delete(duenio);
    }

    @Override
    @Transactional
    public DuenioDto updateDuenio(DuenioDto duenioDto) {
        Duenio duenio = duenioRepository.findById(duenioDto.getId())
                .orElseThrow(() -> new DuenioNotFoundException("Duenio no encontrado!"));

        boolean cambios = false;

        if (updateFieldIfDifferent(duenio::getNombre, duenio::setNombre, duenioDto.getNombre())) {
            cambios = true;
        }
        if (updateFieldIfDifferent(duenio::getApellido, duenio::setApellido, duenioDto.getApellido())) {
            cambios = true;
        }
        if (updateFieldIfDifferent(duenio::getTelefono, duenio::setTelefono, duenioDto.getTelefono())) {
            cambios = true;
        }
        if (updateFieldIfDifferent(duenio::getDireccion, duenio::setDireccion, duenioDto.getDireccion())) {
            cambios = true;
        }
        if (updateFieldIfDifferent(duenio::getEmail, duenio::setEmail, duenioDto.getEmail())) {
            cambios = true;
        }

        if (cambios) {
            duenioRepository.save(duenio);
        }

        return duenioMapper.toDto(duenio);
    }

    private <T> boolean updateFieldIfDifferent(Supplier<T> getter, Consumer<T> setter, T newValue) {
        if (!Objects.equals(getter.get(), newValue)) {
            setter.accept(newValue);
            return true;
        }
        return false;
    }
}
