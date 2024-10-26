package com.veterinaria.exclusive_cats.controller;

import com.veterinaria.exclusive_cats.controller.dto.GatoDto;
import com.veterinaria.exclusive_cats.service.GatoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/gatos")
public class GatoController {

    private final GatoService gatoService;

    public GatoController(GatoService gatoService) {
        this.gatoService = gatoService;
    }

    @PostMapping
    public ResponseEntity<GatoDto> createGato(@Valid @RequestBody GatoDto gatoDto) {
        GatoDto createdGato = gatoService.createGato(gatoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGato);
    }

    @GetMapping
    public ResponseEntity<List<GatoDto>> getGatos() {
        List<GatoDto> gatos = gatoService.getGatos();
        return ResponseEntity.ok(gatos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GatoDto> getGatoById(@PathVariable Long id) {
        GatoDto gato = gatoService.getGatoById(id);
        return ResponseEntity.ok(gato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGato(@PathVariable Long id) {
        gatoService.deleteGato(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GatoDto> updateGato(@PathVariable Long id, @Valid @RequestBody GatoDto gatoDto) {
        gatoDto.setId(id);
        GatoDto updatedGato = gatoService.updateGato(gatoDto);
        return ResponseEntity.ok(updatedGato);
    }
}

