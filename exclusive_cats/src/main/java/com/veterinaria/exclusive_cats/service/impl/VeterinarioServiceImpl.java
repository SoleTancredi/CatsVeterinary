package com.veterinaria.exclusive_cats.service.impl;

import com.veterinaria.exclusive_cats.controller.dto.VeterinarioDto;
import com.veterinaria.exclusive_cats.entity.Veterinario;
import com.veterinaria.exclusive_cats.exceptions.InvalidInputException;
import com.veterinaria.exclusive_cats.exceptions.VeterinarioAlreadyExistsException;
import com.veterinaria.exclusive_cats.exceptions.VeterinarioNotFoundException;
import com.veterinaria.exclusive_cats.repository.VeterinarioRepository;
import com.veterinaria.exclusive_cats.service.GatoService;
import com.veterinaria.exclusive_cats.service.VeterinarioService;
import com.veterinaria.exclusive_cats.service.mapper.VeterinarioMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.veterinaria.exclusive_cats.entity.Veterinario.crearVeterinario;

@Service
public class VeterinarioServiceImpl implements VeterinarioService {
    private final VeterinarioRepository veterinarioRepository;
    private final VeterinarioMapper veterinarioMapper;

    public VeterinarioServiceImpl(VeterinarioRepository veterinarioRepository, VeterinarioMapper veterinarioMapper) {
        this.veterinarioRepository = veterinarioRepository;
        this.veterinarioMapper = veterinarioMapper;
    }


    @Override
    @Transactional
    public VeterinarioDto createVeterinario(VeterinarioDto veterinarioDto) {
        if (veterinarioDto == null) {
            throw new InvalidInputException("El gatoDto no puede ser nulo.");
        }

        veterinarioRepository.findByNombreAndApellido(veterinarioDto.getNombre(), veterinarioDto.getApellido()).ifPresent(existingVeterinario -> {
            throw new VeterinarioAlreadyExistsException(
                    "El veterinario con nombre " + veterinarioDto.getNombre() + " y apellido " + veterinarioDto.getApellido() + " ya existe en la base de datos."
            );
        });

        Veterinario veterinario = crearVeterinario(veterinarioDto.getNombre(), veterinarioDto.getApellido(), veterinarioDto.getEspecialidad());
        Veterinario savedVeterinario = veterinarioRepository.save(veterinario);


        return veterinarioMapper.toDto(savedVeterinario);
    }

    @Override
    public List<VeterinarioDto> getVeterinarios() {
        List<Veterinario> listVeterinarios = veterinarioRepository.findAll();

        return listVeterinarios.stream()
                .map(veterinarioMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public VeterinarioDto getVeterinarioById(Long id) {
        return veterinarioRepository.findById(id)
                .map(veterinarioMapper::toDto)
                .orElseThrow(() -> new VeterinarioNotFoundException("Veterinario no encontrado!"));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Veterinario veterinario = veterinarioRepository.findById(id)
                .orElseThrow(() -> new VeterinarioNotFoundException("Veterinario no encontrado!"));

        veterinarioRepository.delete(veterinario);
    }

    @Override
    @Transactional
    public VeterinarioDto updateVeterinario(VeterinarioDto veterinarioDto) {
        Veterinario veterinario = veterinarioRepository.findById(veterinarioDto.getId())
                .orElseThrow(() -> new VeterinarioNotFoundException("Veterinario no encontrado!"));

        boolean cambios = false;

        if (updateFieldIfDifferent(veterinario::getNombre, veterinario::setNombre, veterinarioDto.getNombre())) {
            cambios = true;
        }
        if (updateFieldIfDifferent(veterinario::getApellido, veterinario::setApellido, veterinarioDto.getApellido())) {
            cambios = true;
        }
        if (updateFieldIfDifferent(veterinario::getEspecialidad, veterinario::setEspecialidad, veterinarioDto.getEspecialidad())) {
            cambios = true;
        }

        Veterinario veterinarioActualizado = new Veterinario();

        if (cambios) {
            veterinarioActualizado = veterinarioRepository.save(veterinario);
        }

        return veterinarioMapper.toDto(veterinarioActualizado);
    }

    private <T> boolean updateFieldIfDifferent(Supplier<T> getter, Consumer<T> setter, T newValue) {
        if (!Objects.equals(getter.get(), newValue)) {
            setter.accept(newValue);
            return true;
        }
        return false;
    }
}
