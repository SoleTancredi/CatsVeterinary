package com.veterinaria.exclusive_cats.entity;

import jakarta.persistence.*;

@Entity
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Enumerated(EnumType.STRING)
    @Column(name = "especialidad", nullable = false)
    private Especialidad especialidad;

    public Veterinario(String nombre, String apellido, Especialidad especialidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
    }

    public static Veterinario crearVeterinario(String nombre, String apellido, Especialidad especialidad) {
        return new Veterinario(nombre, apellido, especialidad);
    }

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Veterinario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", especialidad=" + especialidad +
                '}';
    }
}

