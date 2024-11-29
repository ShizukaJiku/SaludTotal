package com.shizuka.data.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Doctor extends Persona implements Registrable {
    private String especialidad;
    @Builder.Default
    private List<Cita> citas = new ArrayList<Cita>();

    @Override
    public void registrar() {
        System.out.println("Registrando doctor...");
    }

    @Override
    public void actualizar() {
        System.out.println("Actualizando información del doctor...");
    }

    @Override
    public void eliminar() {
        System.out.println("Eliminando doctor...");
    }
}