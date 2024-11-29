package com.shizuka.data.sample;

import com.shizuka.data.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SampleData {

    private static final List<Paciente> pacientes = new ArrayList<>();
    private static final List<Doctor> doctores = new ArrayList<>();
    private static final List<Cita> citas = new ArrayList<>();
    private static final List<HistoriaMedica> historiasMedicas = new ArrayList<>();
    private static final List<Medicamento> medicamentos = new ArrayList<>();
    private static boolean isInitialized = false;

    private static void initializeData() {
        if (isInitialized) return;

        // Inicializar pacientes
        if (pacientes.isEmpty()) {
            pacientes.add(Paciente.builder()
                    .id(1)
                    .nombres("Juan")
                    .apellidos("Pérez")
                    .dni("12345678")
                    .fechaNacimiento(LocalDate.of(1990, 5, 15))
                    .contacto("123456789")
                    .build());

            pacientes.add(Paciente.builder()
                    .id(2)
                    .nombres("Ana")
                    .apellidos("García")
                    .dni("87654321")
                    .fechaNacimiento(LocalDate.of(1985, 8, 25))
                    .contacto("987654321")
                    .build());

            pacientes.add(Paciente.builder()
                    .id(3)
                    .nombres("Carlos")
                    .apellidos("López")
                    .dni("45678901")
                    .fechaNacimiento(LocalDate.of(2000, 2, 10))
                    .contacto("555888222")
                    .build());

            // Pacientes sin citas ni historias médicas
            pacientes.add(Paciente.builder()
                    .id(4)
                    .nombres("Laura")
                    .apellidos("Martínez")
                    .dni("11122233")
                    .fechaNacimiento(LocalDate.of(1993, 3, 5))
                    .contacto("999444888")
                    .build());

            pacientes.add(Paciente.builder()
                    .id(5)
                    .nombres("Diego")
                    .apellidos("Hernández")
                    .dni("33344455")
                    .fechaNacimiento(LocalDate.of(1995, 7, 22))
                    .contacto("555666777")
                    .build());
        }

        // Inicializar doctores
        if (doctores.isEmpty()) {
            doctores.add(Doctor.builder()
                    .id(1)
                    .nombres("Carlos")
                    .apellidos("Gómez")
                    .dni("12345678")
                    .fechaNacimiento(LocalDate.of(1980, 4, 12))
                    .contacto("987654321")
                    .especialidad("Cardiología")
                    .build());

            doctores.add(Doctor.builder()
                    .id(2)
                    .nombres("María")
                    .apellidos("Torres")
                    .dni("87654321")
                    .fechaNacimiento(LocalDate.of(1985, 8, 30))
                    .contacto("123456789")
                    .especialidad("Neurología")
                    .build());

            // Doctores sin citas
            doctores.add(Doctor.builder()
                    .id(3)
                    .nombres("Lucía")
                    .apellidos("Vargas")
                    .dni("55566677")
                    .fechaNacimiento(LocalDate.of(1978, 9, 15))
                    .contacto("444333222")
                    .especialidad("Dermatología")
                    .build());

            doctores.add(Doctor.builder()
                    .id(4)
                    .nombres("Pedro")
                    .apellidos("Ramírez")
                    .dni("99988877")
                    .fechaNacimiento(LocalDate.of(1982, 1, 10))
                    .contacto("222111000")
                    .especialidad("Traumatología")
                    .build());
        }

        // Inicializar citas
        if (citas.isEmpty()) {
            citas.add(new Cita(1, LocalDateTime.of(2024, 12, 1, 10, 30), pacientes.get(0), doctores.get(0)));
            citas.add(new Cita(2, LocalDateTime.of(2024, 12, 2, 15, 0), pacientes.get(1), doctores.get(1)));
            citas.add(new Cita(3, LocalDateTime.of(2024, 12, 3, 9, 0), pacientes.get(2), doctores.get(0)));

            // Asignar las citas a pacientes y doctores
            citas.forEach(cita -> {
                cita.getPaciente().getCitas().add(cita);
                cita.getDoctor().getCitas().add(cita);
            });
        }

        // Inicializar medicamentos
        if (medicamentos.isEmpty()) {
            medicamentos.add(new Medicamento(1, "Paracetamol", 150d, 150));
            medicamentos.add(new Medicamento(2, "Ibuprofeno", 200d, 200));
            medicamentos.add(new Medicamento(3, "Amoxicilina", 100d, 100));
            medicamentos.add(new Medicamento(4, "Clorfenamina", 75d, 75));
            medicamentos.add(new Medicamento(5, "Captopril", 30d, 30));
        }

        // Inicializar historias médicas
        if (historiasMedicas.isEmpty()) {
            historiasMedicas.add(HistoriaMedica.builder()
                    .id(1)
                    .paciente(pacientes.get(0))
                    .diagnostico("Gripe severa")
                    .tratamientos(new ArrayList<>(List.of(
                            new Tratamiento("Paracetamol", "Tomar cada 8 horas por 5 días", medicamentos.get(0))
                    )))
                    .fechaRegistro(LocalDate.of(2024, 11, 15))
                    .build());

            historiasMedicas.add(HistoriaMedica.builder()
                    .id(2)
                    .paciente(pacientes.get(1))
                    .diagnostico("Fractura de brazo")
                    .tratamientos(new ArrayList<>(List.of(
                            new Tratamiento("Ibuprofeno", "Tomar cada 6 horas", medicamentos.get(1)),
                            new Tratamiento("Rehabilitación", "3 sesiones semanales", null)
                    )))
                    .fechaRegistro(LocalDate.of(2024, 11, 20))
                    .build());

            historiasMedicas.add(HistoriaMedica.builder()
                    .id(3)
                    .paciente(pacientes.get(2))
                    .diagnostico("Hipertensión")
                    .tratamientos(new ArrayList<>(List.of(
                            new Tratamiento("Captopril", "Tomar 1 comprimido diario", medicamentos.get(4))
                    )))
                    .fechaRegistro(LocalDate.of(2024, 11, 25))
                    .build());

            // Asignar historias médicas a los pacientes
            historiasMedicas.forEach(historia -> historia.getPaciente().getHistorialMedico().add(historia));
        }

        isInitialized = true;
    }

    public static List<Paciente> getSamplePacienteData() {
        initializeData();
        return pacientes;
    }

    public static List<Doctor> getSampleDoctorData() {
        initializeData();
        return doctores;
    }

    public static List<Cita> getSampleCitaData() {
        initializeData();
        return citas;
    }

    public static List<HistoriaMedica> getSampleHistoriaMedicaData() {
        initializeData();
        return historiasMedicas;
    }

    public static List<Medicamento> getSampleMedicamentoData() {
        initializeData();
        return medicamentos;
    }
}
