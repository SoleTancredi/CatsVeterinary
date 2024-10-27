package com.veterinaria.exclusive_cats.controller;

import com.veterinaria.exclusive_cats.controller.dto.VeterinarioDto;
import com.veterinaria.exclusive_cats.service.VeterinarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veterinarios")
@Validated
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    public VeterinarioController(VeterinarioService veterinarioService) {
        this.veterinarioService = veterinarioService;
    }

    @PostMapping
    public ResponseEntity<VeterinarioDto> createVeterinario(@Valid @RequestBody VeterinarioDto veterinarioDto) {
        VeterinarioDto createdVeterinario = veterinarioService.createVeterinario(veterinarioDto);
        return new ResponseEntity<>(createdVeterinario, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VeterinarioDto>> getAllVeterinarios() {
        List<VeterinarioDto> veterinarios = veterinarioService.getVeterinarios();
        return new ResponseEntity<>(veterinarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeterinarioDto> getVeterinarioById(@PathVariable Long id) {
        VeterinarioDto veterinario = veterinarioService.getVeterinarioById(id);
        return new ResponseEntity<>(veterinario, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeterinarioDto> updateVeterinario(@PathVariable Long id, @Valid @RequestBody VeterinarioDto veterinarioDto) {
        veterinarioDto.setId(id);
        VeterinarioDto updatedVeterinario = veterinarioService.updateVeterinario(veterinarioDto);
        return new ResponseEntity<>(updatedVeterinario, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeterinario(@PathVariable Long id) {
        veterinarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

