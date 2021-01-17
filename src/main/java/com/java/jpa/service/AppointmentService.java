package com.java.jpa.service;

import com.java.jpa.dto.AppointmentDTO;
import com.java.jpa.model.doctor.Doctor;
import com.java.jpa.model.patient.Patient;
import com.java.jpa.repository.doctor.DoctorRepository;
import com.java.jpa.repository.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppointmentService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    private SequenceGenerator sequenceGenerator;

    @Autowired
    PatientRepository patientRepository;

    @Transactional( transactionManager = "mongodbTransactionManager")
    public AppointmentDTO bookAppointment(){

        Patient p = new Patient();
        p.setId(sequenceGenerator.getSequenceNumber(Patient.SEQUENCE_NAME));
        p.setName("xyz");
        p.setEmail("xyyz@gmail.com");
        p.setPhone(987654312l);
        patientRepository.save(p);

        if(true){
            throw new RuntimeException("Something worng .....");
        }

        Doctor doc = new Doctor();
        doc.setName("abc");
        doc.setSpeciality("kidney");
        doc.setExperince(5.6f);

        doctorRepository.save(doc);
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setDoctorName("abc");
        appointmentDTO.setPatientName("xyz");
        return appointmentDTO;
    }
}
