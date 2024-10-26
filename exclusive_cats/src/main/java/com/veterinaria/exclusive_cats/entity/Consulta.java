package com.veterinaria.exclusive_cats.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "veterinario_id", nullable = false)
    private Veterinario veterinarioId;

    @ManyToOne
    @JoinColumn(name = "gato_id", nullable = false)
    private Gato gatoId;

    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column(name = "motivo_consulta", nullable = false, length = 255)
    private String motivoConsulta;

    public Consulta(Veterinario veterinarioId, Gato gatoId, Date fecha, String motivoConsulta) {
        this.veterinarioId = veterinarioId;
        this.gatoId = gatoId;
        this.fecha = fecha;
        this.motivoConsulta = motivoConsulta;
    }

    public static Consulta crearConsulta(Veterinario veterinarioId, Gato gatoId, Date fecha, String motivoConsulta) {
        return new Consulta(veterinarioId, gatoId, fecha, motivoConsulta);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

