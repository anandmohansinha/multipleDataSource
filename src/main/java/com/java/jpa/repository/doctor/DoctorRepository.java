package com.java.jpa.repository.doctor;

import com.java.jpa.model.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    @Modifying
    @Query("update Doctor d set d.name = ?1 where d.id = ?2")
    void setNameById(String name, int id);
}
