package com.shizuka.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Medicamento{
    private int id;
    private String nombre;
    private Double precio;
    private int cantidadDisponible;
}
