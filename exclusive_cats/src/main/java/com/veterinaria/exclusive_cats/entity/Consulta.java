package com.veterinaria.exclusive_cats.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true) // Permite valores nulos
    @JoinColumn(name = "veterinario_id", nullable = true)
    private Veterinario veterinarioId;

    @ManyToOne(optional = true)
    @JoinColumn(name = "gato_id", nullable = true)
    private Gato gatoId;

    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column(name = "motivo_consulta", nullable = false, length = 255)
    private String motivoConsulta;

    public Consulta() {
    }

    public Consulta(Veterinario veterinario, Gato gato, Date fecha, String motivoConsulta) {
        this.veterinarioId = veterinario;
        this.gatoId = gato;
        this.fecha = fecha;
        this.motivoConsulta = motivoConsulta;
    }

    public static Consulta crearConsulta(Veterinario veterinario, Gato gato, Date fecha, String motivoConsulta) {
        return new Consulta(veterinario, gato, fecha, motivoConsulta);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Veterinario getVeterinarioId() {
        return veterinarioId;
    }

    public void setVeterinarioId(Veterinario veterinarioId) {
        this.veterinarioId = veterinarioId;
    }

    public Gato getGatoId() {
        return gatoId;
    }

    public void setGatoId(Gato gatoId) {
        this.gatoId = gatoId;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", veterinarioId=" + veterinarioId +
                ", gatoId=" + gatoId +
                ", fecha=" + fecha +
                ", motivoConsulta='" + motivoConsulta + '\'' +
                '}';
    }
}

