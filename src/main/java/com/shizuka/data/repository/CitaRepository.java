package com.shizuka.data.repository;

import com.shizuka.data.model.Cita;
import com.shizuka.data.sample.SampleData;

import java.util.List;
import java.util.Optional;

public class CitaRepository implements Repository<Cita> {

    private final List<Cita> citas = SampleData.getSampleCitaData();

    @Override
    public Cita insert(Cita cita) {
        citas.add(cita);
        return cita;
    }

    @Override
    public Optional<Cita> findById(int id) {
        return citas.stream()
                .filter(cita -> cita.getId() == id)
                .findFirst();
    }

    @Override
    public List<Cita> getAll() {
        return citas;
    }

    @Override
    public boolean update(int id, Cita updatedCita) {
        Optional<Cita> existingCita = findById(id);
        if (existingCita.isPresent()) {
            Cita cita = existingCita.get();
            cita.setFechaHora(updatedCita.getFechaHora());
            cita.setPaciente(updatedCita.getPaciente());
            cita.setDoctor(updatedCita.getDoctor());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return citas.removeIf(cita -> cita.getId() == id);
    }
}
