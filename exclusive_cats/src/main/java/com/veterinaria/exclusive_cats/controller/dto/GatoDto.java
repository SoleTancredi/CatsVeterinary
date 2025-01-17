package com.veterinaria.exclusive_cats.controller.dto;

import jakarta.validation.constraints.*;

public class GatoDto {
    private Long id;

    @NotBlank(message = "El nombre del gato es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @Min(value = 0, message = "La edad no puede ser negativa")
    @Max(value = 30, message = "La edad no puede ser mayor a 30 años") // Opcional, para limitar la edad de los gatos
    private int edad;

    @NotBlank(message = "La raza del gato es obligatoria")
    @Size(min = 2, max = 50, message = "La raza debe tener entre 2 y 50 caracteres")
    private String raza;

    @NotNull(message = "El id del dueño es obligatorio")
    private Long duenioId;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Long getDuenioId() {
        return duenioId;
    }

    public void setDuenioId(Long duenioId) {
        this.duenioId = duenioId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
