package com.shizuka.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class Inventario implements Gestionable<Medicamento> {
    @Builder.Default
    private List<Medicamento> medicamentos = new ArrayList<>();

    public Medicamento buscarMedicamento(String nombre) {
        return medicamentos.stream()
                .filter(med -> med.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void agregar(Medicamento medicamento) {
        listar().add(medicamento);
    }

    @Override
    public void eliminar(int id) {
        System.out.println("Eliminando medicamento del inventario...");
    }

    @Override
    public Medicamento findById(int id) {
        return medicamentos.stream().filter((med) -> med.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Medicamento> listar() {
        return medicamentos;
    }
}