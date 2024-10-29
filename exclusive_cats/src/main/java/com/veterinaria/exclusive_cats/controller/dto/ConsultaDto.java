package com.veterinaria.exclusive_cats.controller.dto;

import com.veterinaria.exclusive_cats.entity.Gato;
import com.veterinaria.exclusive_cats.entity.Veterinario;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Date;

public class ConsultaDto {
    private Long id;
    @NotNull(message = "El veterinario es obligatorio.")
    private Long veterinarioId;

    @NotNull(message = "El gato es obligatorio.")
    private Long gatoId;

    @NotNull(message = "La fecha es obligatoria.")
    @FutureOrPresent(message = "La fecha debe ser hoy o en el futuro.")
    private Date fecha;

    @NotBlank(message = "El motivo de consulta es obligatorio.")
    @Size(max = 255, message = "El motivo de consulta no puede exceder 255 caracteres.")
    private String motivoConsulta;


    public Long getVeterinarioId() {
        return veterinarioId;
    }

    public void setVeterinarioId(Long veterinarioId) {
        this.veterinarioId = veterinarioId;
    }

    public Long getGatoId() {
        return gatoId;
    }

    public void setGatoId(Long gatoId) {
        this.gatoId = gatoId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
