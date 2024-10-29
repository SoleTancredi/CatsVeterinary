package com.veterinaria.exclusive_cats.service.impl;

import com.veterinaria.exclusive_cats.controller.dto.GatoDto;
import com.veterinaria.exclusive_cats.entity.Duenio;
import com.veterinaria.exclusive_cats.entity.Gato;
import com.veterinaria.exclusive_cats.exceptions.*;
import com.veterinaria.exclusive_cats.repository.DuenioRepository;
import com.veterinaria.exclusive_cats.repository.GatoRepository;
import com.veterinaria.exclusive_cats.service.GatoService;
import com.veterinaria.exclusive_cats.service.mapper.GatoMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.veterinaria.exclusive_cats.entity.Gato.crearGato;
@Service
public class GatoServiceImpl implements GatoService {
    private final GatoRepository gatoRepository;
    private final DuenioRepository duenioRepository;
    private final GatoMapper gatoMapper;
    public GatoServiceImpl(GatoRepository gatoRepository, DuenioRepository duenioRepository, GatoMapper gatoMapper) {
        this.gatoRepository = gatoRepository;
        this.duenioRepository = duenioRepository;
        this.gatoMapper = gatoMapper;
    }

    @Override
    @Transactional //si ocurre alguna excepción en cualquier punto de la transacción, los cambios en la base de datos se deshacen automáticamente
    public GatoDto createGato(GatoDto gatoDto) {

        if (gatoDto == null) {
            throw new InvalidInputException("El gatoDto no puede ser nulo.");
        }

        gatoRepository.findByNombreAndEdad(gatoDto.getNombre(), gatoDto.getEdad()).ifPresent(existingGato -> {
            throw new GatoAlreadyExistsException(
                    "El gato con nombre " + gatoDto.getNombre() + " y edad " + gatoDto.getEdad() + " ya existe en la base de datos."
            );
        });

        Duenio duenio = duenioRepository.findById(gatoDto.getDuenioId())
                .orElseThrow(() -> new DuenioNotFoundException("No se encontró el dueño con ID: " + gatoDto.getDuenioId()));

        Gato gato = crearGato(gatoDto.getNombre(), gatoDto.getEdad(), gatoDto.getRaza(), duenio);
        Gato savedGato = gatoRepository.save(gato);

        return gatoMapper.toDto(savedGato);
    }

    @Override
    public List<GatoDto> getGatos() {
        List<Gato> listGatos = gatoRepository.findAll();

        return listGatos.stream()
                .map(gatoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GatoDto getGatoById(Long id) {
        return gatoRepository.findById(id)
                .map(gatoMapper::toDto)
                .orElseThrow(() -> new GatoNotFoundException("Gato no encontrado!"));
    }

    @Override
    @Transactional
    public void deleteGato(Long id) {
        Gato gato = gatoRepository.findById(id)
                .orElseThrow(() -> new GatoNotFoundException("Gato no encontrado!"));

        gatoRepository.delete(gato);
    }

    @Override
    @Transactional
    public GatoDto updateGato(GatoDto gatoDto) {
        Gato gato = gatoRepository.findById(gatoDto.getId())
                .orElseThrow(() -> new GatoNotFoundException("Gato no encontrado!"));

        boolean cambios = false;

        if (updateFieldIfDifferent(gato::getNombre, gato::setNombre, gatoDto.getNombre())) {
            cambios = true;
        }
        if (updateFieldIfDifferent(gato::getEdad, gato::setEdad, gatoDto.getEdad())) {
            cambios = true;
        }
        if (updateFieldIfDifferent(gato::getRaza, gato::setRaza, gatoDto.getRaza())) {
            cambios = true;
        }

        if (cambios) {
            gatoRepository.save(gato);
        }

        return gatoMapper.toDto(gato);
    }

    private <T> boolean updateFieldIfDifferent(Supplier<T> getter, Consumer<T> setter, T newValue) {
        if (!Objects.equals(getter.get(), newValue)) {
            setter.accept(newValue);
            return true;
        }
        return false;
    }
}
