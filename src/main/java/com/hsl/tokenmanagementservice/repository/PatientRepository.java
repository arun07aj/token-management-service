package com.hsl.tokenmanagementservice.repository;

import com.hsl.tokenmanagementservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findPatientByOpNumber(String opNumber);
}
