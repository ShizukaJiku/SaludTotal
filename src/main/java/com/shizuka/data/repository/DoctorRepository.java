package com.shizuka.data.repository;

import com.shizuka.data.model.Doctor;
import com.shizuka.data.sample.SampleData;

import java.util.List;
import java.util.Optional;

public class DoctorRepository implements Repository<Doctor> {

    private final List<Doctor> doctores = SampleData.getSampleDoctorData();

    @Override
    public Doctor insert(Doctor doctor) {
        doctores.add(doctor);
        return doctor;
    }

    @Override
    public Optional<Doctor> findById(int id) {
        return doctores.stream()
                .filter(doctor -> doctor.getId() == id)
                .findFirst();
    }

    @Override
    public List<Doctor> getAll() {
        return doctores;
    }

    @Override
    public boolean update(int id, Doctor updatedDoctor) {
        Optional<Doctor> existingDoctor = findById(id);
        if (existingDoctor.isPresent()) {
            Doctor doctor = existingDoctor.get();
            doctor.setNombres(updatedDoctor.getNombres());
            doctor.setApellidos(updatedDoctor.getApellidos());
            doctor.setEspecialidad(updatedDoctor.getEspecialidad());
            doctor.setContacto(updatedDoctor.getContacto());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return doctores.removeIf(doctor -> doctor.getId() == id);
    }
}
