package com.shizuka.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@SuperBuilder
public abstract class Persona {
    private int id;
    private String nombres;
    private String apellidos;
    private String dni;
    private LocalDate fechaNacimiento;
    private String contacto;

    public String obtenerNombreCompleto() {
        return nombres + " " + apellidos;
    }
}

