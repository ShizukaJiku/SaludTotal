package com.shizuka.ui.model;

import com.shizuka.data.model.Medicamento;

public class ModelMedicamento {

    public static Object[] toTableRowCustom(Medicamento m, int row) {
        return new Object[]{
                false,
                row,
                m.getNombre(),
                "S/. " + m.getPrecio().toString(),
                m.getCantidadDisponible()
        };
    }
}