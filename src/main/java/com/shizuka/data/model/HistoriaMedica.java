package com.shizuka.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class HistoriaMedica {
    private int id;
    private String diagnostico;
    @Builder.Default
    private List<Tratamiento> tratamientos = new ArrayList<>();
    private Paciente paciente;
    private LocalDate fechaRegistro;

    public String tratamientosToString() {
        StringBuilder tratamientosString = new StringBuilder();

        for (Tratamiento tratamiento : tratamientos) {
            Medicamento medicamento = tratamiento.getMedicamento();

            if(medicamento == null) continue;

            tratamientosString.append(medicamento.getNombre()).append(", ");
        }

        return tratamientosString.toString();
    }
}

