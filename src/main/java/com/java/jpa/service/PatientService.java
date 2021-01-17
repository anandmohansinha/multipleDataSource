package com.java.jpa.service;

import com.java.jpa.model.patient.Patient;
import com.java.jpa.repository.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    public List<Patient> getAll(){
        return patientRepository.findAll();
    }

    public void savePatient(Patient patient){
        patientRepository.save(patient);
    }

    public Patient updatePatient( String name, int id){
        Optional<Patient> patient = patientRepository.findById(id);
        Patient p = patient.get();
        p.setName(name);
        Patient savedPatient = patientRepository.save(p);
        return savedPatient;
    }

    public void deletePatient(Integer id){
        patientRepository.deleteById(id);
    }

}
