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
public class Paciente extends Persona implements Registrable {

    @Builder.Default
    private List<Cita> citas = new ArrayList<>();
    @Builder.Default
    private List<HistoriaMedica> historialMedico = new ArrayList<>();

    // Implementación de métodos de Registrable
    @Override
    public void registrar() {
        System.out.println("Registrando paciente...");
    }

    @Override
    public void actualizar() {
        System.out.println("Actualizando información del paciente...");
    }

    @Override
    public void eliminar() {
        System.out.println("Eliminando paciente...");
    }
}

