package com.veterinaria.exclusive_cats.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "duenio")
public class Duenio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "telefono", nullable = true, length = 15)
    private String telefono;

    @Column(name = "direccion", nullable = false, length = 100)
    private String direccion;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Duenio(String nombre, String apellido, String telefono, String direccion, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
    }

    public static Duenio crearDuenio(String nombre, String apellido, String telefono, String direccion, String email) {
        return new Duenio(nombre, apellido, telefono, direccion, email);
    }

    @Override
    public String toString() {
        return "Duenio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
