package com.shizuka.ui.model;

import com.shizuka.data.model.HistoriaMedica;

public class ModelHistoriaMedica {

    public static Object[] toTableRowCustom(HistoriaMedica historia, int row) {

        StringBuilder tratamientos = new StringBuilder();

        tratamientos.append("<tr>");
        tratamientos.append("<td>");

        return new Object[]{
                false,
                row,
                historia.getPaciente().obtenerNombreCompleto(),
                historia.tratamientosToString(),
                historia.getDiagnostico(),
                historia.getFechaRegistro().toString()
        };
    }
}
