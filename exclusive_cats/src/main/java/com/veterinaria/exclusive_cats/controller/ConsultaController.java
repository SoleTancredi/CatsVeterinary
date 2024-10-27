package com.veterinaria.exclusive_cats.controller;

import com.veterinaria.exclusive_cats.controller.dto.ConsultaDto;
import com.veterinaria.exclusive_cats.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
@Validated
public class ConsultaController {

    private final ConsultaService consultaService;

    @Autowired
    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    public ResponseEntity<ConsultaDto> createConsulta(@Valid @RequestBody ConsultaDto consultaDto) {
        ConsultaDto createdConsulta = consultaService.createConsulta(consultaDto);
        return new ResponseEntity<>(createdConsulta, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ConsultaDto>> getAllConsultas() {
        List<ConsultaDto> consultas = consultaService.getConsultas();
        return new ResponseEntity<>(consultas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDto> getConsultaById(@PathVariable Long id) {
        ConsultaDto consulta = consultaService.getConsultaById(id);
        return new ResponseEntity<>(consulta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaDto> updateConsulta(@PathVariable Long id, @Valid @RequestBody ConsultaDto consultaDto) {
        consultaDto.setId(id);
        ConsultaDto updatedConsulta = consultaService.updateConsulta(consultaDto);
        return new ResponseEntity<>(updatedConsulta, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsulta(@PathVariable Long id) {
        consultaService.deleteConsulta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
