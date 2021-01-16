package com.java.jpa.controller;

import com.java.jpa.model.doctor.Doctor;
import com.java.jpa.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DoctorController {
    @Autowired
    private DoctorService service;

    @GetMapping("/allDoctor")
    public ResponseEntity<Doctor> getAll(){
        List<Doctor> doctorList = service.getAllDoctor();
        ResponseEntity<Doctor> responseEntity = new ResponseEntity(doctorList, HttpStatus.OK);
        return responseEntity;
    }
    @GetMapping("/doctor/{id}")
    public ResponseEntity<Doctor> findDoctor(@PathVariable int id){
        Optional<Doctor> doc = service.findById(id);
        ResponseEntity<Doctor> response = new ResponseEntity(doc.get(), HttpStatus.OK);
        return response;
    }

    @PostMapping("/saveDoctor")
    public ResponseEntity<String> saveDoc(@RequestBody Doctor doctor){
        service.save(doctor);
        ResponseEntity<String> response = new ResponseEntity<>("success", HttpStatus.OK);
        return response;
    }

    @PutMapping("/update/{id}/{name}")
    public ResponseEntity<String> updateName(@PathVariable("id") int id, @PathVariable("name") String name){
        service.updateDoctorName(name, id);
        ResponseEntity<String> response = new ResponseEntity<>("success", HttpStatus.OK);
        return response;
    }

}
