package com.shizuka.ui.model;

import com.shizuka.data.model.Paciente;

public class ModelPaciente {

    public static Object[] toTableRowCustom(Paciente p, int row) {
        String lastCita = p.getCitas().isEmpty()
                ? "No hay cita programada"
                : p.getCitas().getLast().getFechaHora().toString();

        return new Object[]{
                false,
                row,
                p.getNombres(),
                p.getApellidos(),
                p.getDni(),
                p.getFechaNacimiento().toString(),
                p.getContacto(),
                p.getCitas().size(),
                lastCita
        };
    }

}
