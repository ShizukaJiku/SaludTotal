package com.shizuka.ui.model;

import com.shizuka.data.model.Doctor;

public class ModelDoctor {

    public static Object[] toTableRowCustom(Doctor d, int row) {
        return new Object[]{
                false,
                row,
                d.getNombres(),
                d.getApellidos(),
                d.getDni(),
                d.getEspecialidad(),
                d.getCitas().size()
        };
    }
}
