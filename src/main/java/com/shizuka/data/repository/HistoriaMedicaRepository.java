package com.shizuka.data.repository;

import com.shizuka.data.model.HistoriaMedica;
import com.shizuka.data.sample.SampleData;

import java.util.List;
import java.util.Optional;

public class HistoriaMedicaRepository implements Repository<HistoriaMedica> {

    private final List<HistoriaMedica> historiasMedicas = SampleData.getSampleHistoriaMedicaData();

    @Override
    public HistoriaMedica insert(HistoriaMedica historia) {
        historiasMedicas.add(historia);
        return historia;
    }

    @Override
    public Optional<HistoriaMedica> findById(int id) {
        return historiasMedicas.stream()
                .filter(historia -> historia.getId() == id)
                .findFirst();
    }

    @Override
    public List<HistoriaMedica> getAll() {
        return historiasMedicas;
    }

    @Override
    public boolean update(int id, HistoriaMedica updatedHistoria) {
        Optional<HistoriaMedica> existingHistoria = findById(id);
        if (existingHistoria.isPresent()) {
            HistoriaMedica historia = existingHistoria.get();
            historia.setDiagnostico(updatedHistoria.getDiagnostico());
            historia.setTratamientos(updatedHistoria.getTratamientos());
            historia.setFechaRegistro(updatedHistoria.getFechaRegistro());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return historiasMedicas.removeIf(historia -> historia.getId() == id);
    }
}
