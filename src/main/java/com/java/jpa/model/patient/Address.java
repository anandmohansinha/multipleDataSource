package com.java.jpa.model.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Address {

    @Transient
    public static final String SEQUENCE_NAME= "address_sequence";

    @Id
    private Integer id;
    private String houseNo;
    private String street;
    private String city;
    private String state;

    /**
     * how to do it OneToMany in spring data mongodb
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
