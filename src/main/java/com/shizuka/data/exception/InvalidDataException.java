package com.shizuka.data.exception;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String message) {
        super("Datos inválidos: " + message);
    }
}