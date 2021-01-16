package com.java.jpa.service;

import com.java.jpa.model.doctor.Doctor;
import com.java.jpa.repository.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repository;

    public List<Doctor> getAllDoctor(){
        return repository.findAll();
    }
    public void save(Doctor doctor){
        repository.save(doctor);
    }

    public Optional<Doctor> findById(int id){
        return repository.findById(id);
    }

    @Transactional
    public void updateDoctorName(String name, int id){
        repository.setNameById(name, id);
    }
}
