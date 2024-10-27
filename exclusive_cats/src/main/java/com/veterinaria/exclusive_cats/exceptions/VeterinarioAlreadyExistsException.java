package com.veterinaria.exclusive_cats.exceptions;

public class VeterinarioAlreadyExistsException extends RuntimeException {
    public VeterinarioAlreadyExistsException(String message) {
        super(message);
    }
}
