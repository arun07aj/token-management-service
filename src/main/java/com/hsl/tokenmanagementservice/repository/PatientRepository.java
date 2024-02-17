package com.hsl.tokenmanagementservice.repository;

import com.hsl.tokenmanagementservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}