package com.shizuka.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tratamiento {
    private String nombre;
    private String dosis;
    private Medicamento medicamento;
}