package com.shizuka.data.repository;

import com.shizuka.data.exception.InvalidDataException;
import com.shizuka.data.exception.PersonaExistException;
import com.shizuka.data.exception.PersonaNotFoundException;
import com.shizuka.data.model.Paciente;
import com.shizuka.data.sample.SampleData;

import java.util.List;
import java.util.Optional;

public class PacienteRepository implements Repository<Paciente> {

    private final List<Paciente> pacientes = SampleData.getSamplePacienteData();

    @Override
    public Paciente insert(Paciente paciente) {
        // Verificar si ya existe un paciente con el mismo DNI
        boolean exists = pacientes.stream()
                .anyMatch(existingPaciente -> existingPaciente.getDni().equals(paciente.getDni()));

        if (exists) {
            throw new PersonaExistException(paciente.getDni());
        }

        // Si no existe, agregar al repositorio
        pacientes.add(paciente);
        return paciente;
    }

    @Override
    public List<Paciente> getAll() {
        return pacientes;
    }

    @Override
    public Optional<Paciente> findById(int id) {
        return pacientes.stream()
                .filter(paciente -> paciente.getId() == id)
                .findFirst();
    }

    public Paciente findByIdOrThrow(int id) {
        return findById(id)
                .orElseThrow(() -> new PersonaNotFoundException(id));
    }

    @Override
    public boolean update(int id, Paciente updatedPaciente) {
        Paciente existingPaciente = findByIdOrThrow(id);

        if (updatedPaciente.getNombres() == null || updatedPaciente.getNombres().isEmpty()) {
            throw new InvalidDataException("El nombre no puede estar vacío.");
        }

        existingPaciente.setNombres(updatedPaciente.getNombres());
        existingPaciente.setApellidos(updatedPaciente.getApellidos());
        existingPaciente.setFechaNacimiento(updatedPaciente.getFechaNacimiento());
        existingPaciente.setContacto(updatedPaciente.getContacto());
        return true;
    }

    @Override
    public boolean delete(int id) {
        return pacientes.removeIf(paciente -> paciente.getId() == id);
    }
}
