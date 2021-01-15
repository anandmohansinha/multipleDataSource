package com.java.jpa.model.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.Transient;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Patient {
    @Transient
    public static final String SEQUENCE_NAME= "patient_sequence";
    @Id
    int id;
    String name;
    String email;
    Long phone;

    //Address address;

}
