package com.shizuka.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistroMedico {
    private String descripcion;
    private Tratamiento tratamiento;
}