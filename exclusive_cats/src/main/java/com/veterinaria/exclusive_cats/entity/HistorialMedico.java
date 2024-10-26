package com.veterinaria.exclusive_cats.entity;

import jakarta.persistence.*;

import javax.persistence.*;
import java.util.List;

@Entity
public class HistorialMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_gato", nullable = false)
    private Long idGato;

    @OneToMany(mappedBy = "historialMedico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consulta> consultas;

    // Constructor sin parámetros
    public HistorialMedico() {}

    public HistorialMedico(Long idGato, List<Consulta> consultas) {
        this.idGato = idGato;
        this.consultas = consultas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdGato() {
        return idGato;
    }

    public void setIdGato(Long idGato) {
        this.idGato = idGato;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    @Override
    public String toString() {
        return "HistorialMedico{" +
                "id=" + id +
                ", idGato=" + idGato +
                ", consultas=" + consultas +
                '}';
    }
}

