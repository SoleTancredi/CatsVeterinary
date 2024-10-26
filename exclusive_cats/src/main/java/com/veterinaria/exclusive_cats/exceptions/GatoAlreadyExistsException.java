package com.veterinaria.exclusive_cats.exceptions;

public class GatoAlreadyExistsException extends RuntimeException {

    public GatoAlreadyExistsException(String message) {
        super(message);
    }
}