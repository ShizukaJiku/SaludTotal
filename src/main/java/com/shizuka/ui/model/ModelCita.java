package com.shizuka.ui.model;

import com.shizuka.data.model.Cita;

public class ModelCita {

    public static Object[] toTableRowCustom(Cita c, int row) {
        return new Object[]{
                false,
                row,
                c.getPaciente().obtenerNombreCompleto(),
                c.getDoctor().obtenerNombreCompleto(),
                c.getFechaHora().toLocalDate().toString(),
                c.getFechaHora().toLocalTime().toString()
        };
    }
}
