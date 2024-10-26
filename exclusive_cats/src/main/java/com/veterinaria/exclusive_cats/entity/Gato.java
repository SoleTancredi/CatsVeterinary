package com.veterinaria.exclusive_cats.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "gato")
public class Gato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "edad", nullable = false)
    private int edad;

    @Column(name = "raza", nullable = false, length = 50)
    private String raza;

    @OneToOne
    @JoinColumn(name = "historial_medico_id")
    private HistorialMedico historialMedicoId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "duenio_id")
    private Duenio duenioId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public HistorialMedico getHistorialMedicoId() {
        return historialMedicoId;
    }

    public void setHistorialMedicoId(HistorialMedico historialMedicoId) {
        this.historialMedicoId = historialMedicoId;
    }

    public Duenio getDuenioId() {
        return duenioId;
    }

    public void setDuenioId(Duenio duenioId) {
        this.duenioId = duenioId;
    }

    public Gato(Long id, String nombre, int edad, String raza, HistorialMedico historialMedicoId, Duenio duenioId) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.raza = raza;
        this.historialMedicoId = historialMedicoId;
        this.duenioId = duenioId;
    }

    public static Gato crearGato(String nombre, int edad, String raza, HistorialMedico historialMedicoId, Duenio duenioId) {
        return new Gato(null, nombre, edad, raza, historialMedicoId, duenioId);
    }

    @Override
    public String toString() {
        return "Gato{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", raza='" + raza + '\'' +
                ", duenioId=" + duenioId +
                '}';
    }
}
