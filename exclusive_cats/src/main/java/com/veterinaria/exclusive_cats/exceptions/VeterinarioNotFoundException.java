package com.veterinaria.exclusive_cats.exceptions;

public class VeterinarioNotFoundException extends RuntimeException{
    public VeterinarioNotFoundException(String message) {
        super(message);
    }
}
