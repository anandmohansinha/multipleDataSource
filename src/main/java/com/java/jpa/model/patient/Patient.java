package com.java.jpa.model.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.Set;

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
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    Set<Address> address;


}
