package com.shizuka.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
public class Cita {
    private int id;
    @Builder.Default
    private LocalDateTime fechaHora = LocalDateTime.now();
    private Paciente paciente;
    private Doctor doctor;
}

