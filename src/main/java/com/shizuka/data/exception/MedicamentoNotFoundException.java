package com.shizuka.data.exception;

public class MedicamentoNotFoundException extends RuntimeException {
    public MedicamentoNotFoundException(int id) {
        super("No se encontró un medicamento con el ID: " + id);
    }
}