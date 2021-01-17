package com.java.jpa.controller;

import com.java.jpa.dto.AppointmentDTO;
import com.java.jpa.model.patient.Address;
import com.java.jpa.model.patient.Patient;
import com.java.jpa.service.AppointmentService;
import com.java.jpa.service.PatientService;
import com.java.jpa.service.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.SequenceGenerators;
import java.util.List;
import java.util.Set;

@RestController
public class PatientController {

    @Autowired
    PatientService service;

    @Autowired
    AppointmentService appointmentService;

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
        Set<Address> addressSet = patient.getAddress();
        for(Address address: addressSet){
            address.setId(sequenceGenerator.getSequenceNumber(Address.SEQUENCE_NAME));
        }
        service.savePatient(patient);
        ResponseEntity<String> response = new ResponseEntity<>("Success", HttpStatus.OK);
        return response;
    }

    @PutMapping("/updateName/{id}/{name}")
    public ResponseEntity<Patient> updatePatientName(@PathVariable("id") int id,  @PathVariable("name") String name){
        Patient patient = service.updatePatient(name, id);
        ResponseEntity<Patient> response = new ResponseEntity(patient, HttpStatus.OK);
        return response;
    }

    @GetMapping("/getAppointment")
    public ResponseEntity<AppointmentDTO> getAppoitment(){
        AppointmentDTO appointmentDTO = appointmentService.bookAppointment();
        ResponseEntity<AppointmentDTO> response = new ResponseEntity<AppointmentDTO>(appointmentDTO, HttpStatus.OK);
        return response;
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable("id") int id){

        service.deletePatient(id);
        ResponseEntity<String> response = new ResponseEntity<String>("deleted", HttpStatus.OK);
        return response;
    }

}
