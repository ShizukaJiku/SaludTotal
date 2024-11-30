package com.shizuka.data.exception;

public class CitaNotFoundException extends RuntimeException {
    public CitaNotFoundException(int id) {
        super("No se encontró una cita con el ID: " + id);
    }
}