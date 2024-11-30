package com.shizuka.data.exception;

public class PersonaExistException extends RuntimeException {

    public PersonaExistException(String dni) {
        super("Ya existe una persona con el dni: " + dni);
    }

}
