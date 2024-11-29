package com.shizuka.data.repository;

import com.shizuka.data.model.Paciente;
import com.shizuka.data.sample.SampleData;

import java.util.List;
import java.util.Optional;

public class PacienteRepository implements Repository<Paciente> {

    private final List<Paciente> pacientes = SampleData.getSamplePacienteData();

    public Paciente insert(Paciente paciente) {
        pacientes.add(paciente);
        return paciente;
    }

    public Optional<Paciente> findById(int id) {
        return pacientes.stream()
                .filter(paciente -> paciente.getId() == id)
                .findFirst();
    }

    public List<Paciente> getAll() {
        return pacientes;
    }

    public boolean update(int id, Paciente updatedPacient) {
        Optional<Paciente> existingPacient = findById(id);
        if (existingPacient.isPresent()) {
            Paciente paciente = existingPacient.get();
            paciente.setNombres(updatedPacient.getNombres());
            paciente.setApellidos(updatedPacient.getApellidos());
            paciente.setFechaNacimiento(updatedPacient.getFechaNacimiento());
            paciente.setContacto(updatedPacient.getContacto());
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        return pacientes.removeIf(paciente -> paciente.getId() == id);
    }
}
