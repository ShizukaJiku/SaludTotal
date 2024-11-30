package com.shizuka.data.repository;

import com.shizuka.data.exception.HistoriaMedicaNotFoundException;
import com.shizuka.data.exception.MedicamentoNotFoundException;
import com.shizuka.data.model.HistoriaMedica;
import com.shizuka.data.model.Medicamento;
import com.shizuka.data.sample.SampleData;

import java.util.List;
import java.util.Optional;

public class MedicamentoRepository implements Repository<Medicamento> {

    private final List<Medicamento> medicamentos = SampleData.getSampleMedicamentoData();

    @Override
    public Medicamento insert(Medicamento medicamento) {
        medicamentos.add(medicamento);
        return medicamento;
    }

    @Override
    public Optional<Medicamento> findById(int id) {
        return medicamentos.stream()
                .filter(medicamento -> medicamento.getId() == id)
                .findFirst();
    }

    public Medicamento findByIdOrThrow(int id) {
        return findById(id)
                .orElseThrow(() -> new MedicamentoNotFoundException(id));
    }

    @Override
    public List<Medicamento> getAll() {
        return medicamentos;
    }

    @Override
    public boolean update(int id, Medicamento updatedMedicamento) {
        Optional<Medicamento> existingMedicamento = findById(id);
        if (existingMedicamento.isPresent()) {
            Medicamento medicamento = existingMedicamento.get();
            medicamento.setNombre(updatedMedicamento.getNombre());
            medicamento.setCantidadDisponible(updatedMedicamento.getCantidadDisponible());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return medicamentos.remove(findByIdOrThrow(id));
    }
}
