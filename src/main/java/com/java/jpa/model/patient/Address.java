package com.java.jpa.model.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Address {

    @Id
    private Integer id;
    private String houseNo;
    private String street;
    private String city;
    private String state;
}
