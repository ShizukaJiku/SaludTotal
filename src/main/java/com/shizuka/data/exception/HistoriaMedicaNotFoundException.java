package com.shizuka.data.exception;

public class HistoriaMedicaNotFoundException extends RuntimeException {
    public HistoriaMedicaNotFoundException(int id) {
        super("No se encontró una historia médica con el ID: " + id);
    }
}