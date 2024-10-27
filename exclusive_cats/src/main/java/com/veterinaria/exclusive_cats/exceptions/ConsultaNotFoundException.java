package com.veterinaria.exclusive_cats.exceptions;

public class ConsultaNotFoundException extends RuntimeException{
    public ConsultaNotFoundException(String message) {
        super(message);
    }
}
