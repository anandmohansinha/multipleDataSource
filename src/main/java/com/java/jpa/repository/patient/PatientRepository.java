package com.java.jpa.repository.patient;

import com.java.jpa.model.patient.Patient;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<Patient, Integer> {




    /*@Modifying
    @Query("Update Patient p SET p.name=:name WHERE p.id=:id")
    public void updateTitle(@Param("id") ObjectId id, @Param("name") String name);
*/



}
