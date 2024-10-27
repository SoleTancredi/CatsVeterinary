package com.veterinaria.exclusive_cats.controller;

import com.veterinaria.exclusive_cats.controller.dto.DuenioDto;
import com.veterinaria.exclusive_cats.service.DuenioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping("/api/duenios")
@Validated
public class DuenioController {

    private final DuenioService duenioService;

    public DuenioController(DuenioService duenioService) {
        this.duenioService = duenioService;
    }

    @PostMapping
    public ResponseEntity<DuenioDto> createDuenio(@Valid @RequestBody DuenioDto duenioDto) {
        DuenioDto createdDuenio = duenioService.createDuenio(duenioDto);
        return new ResponseEntity<>(createdDuenio, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DuenioDto>> getAllDuenios() {
        List<DuenioDto> duenios = duenioService.getDuenios();
        return new ResponseEntity<>(duenios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DuenioDto> getDuenioById(@PathVariable Long id) {
        DuenioDto duenio = duenioService.getDuenioById(id);
        return new ResponseEntity<>(duenio, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DuenioDto> updateDuenio(@PathVariable Long id, @Valid @RequestBody DuenioDto duenioDto) {
        duenioDto.setId(id);
        DuenioDto updatedDuenio = duenioService.updateDuenio(duenioDto);
        return new ResponseEntity<>(updatedDuenio, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDuenio(@PathVariable Long id) {
        duenioService.deleteDuenio(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


