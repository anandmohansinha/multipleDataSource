package com.java.jpa.controller;

import com.java.jpa.model.patient.Patient;
import com.java.jpa.service.PatientService;
import com.java.jpa.service.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.SequenceGenerators;
import java.util.List;

@RestController
public class PatientController {

    @Autowired
    PatientService service;

    @Autowired
    private SequenceGenerator sequenceGenerator;

    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getAllPatient(){
        List<Patient> patients = service.getAll();
        ResponseEntity<List<Patient>> response = new ResponseEntity(patients, HttpStatus.OK);
        return response;
    }

    @PostMapping("/save")
    public ResponseEntity<String> savePatient(@RequestBody Patient patient){
        // generate sequence
        patient.setId(sequenceGenerator.getSequenceNumber(Patient.SEQUENCE_NAME));
        service.savePatient(patient);
        ResponseEntity<String> response = new ResponseEntity<>("Success", HttpStatus.OK);
        return response;
    }
}
