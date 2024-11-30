package com.shizuka.data.exception;

public class PersonaNotFoundException extends RuntimeException {
    public PersonaNotFoundException(int id) {
        super("No se encontró una persona con el ID: " + id);
    }
}