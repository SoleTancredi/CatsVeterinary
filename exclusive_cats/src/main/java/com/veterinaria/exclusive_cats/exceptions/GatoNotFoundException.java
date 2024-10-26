package com.veterinaria.exclusive_cats.exceptions;

public class GatoNotFoundException extends RuntimeException {
    public GatoNotFoundException(String mensaje) {
        super(mensaje);
    }
}
