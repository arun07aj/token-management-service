package com.hsl.tokenmanagementservice.controller;

import com.hsl.tokenmanagementservice.dto.PostPatientDTO;
import com.hsl.tokenmanagementservice.exception.BadRequestException;
import com.hsl.tokenmanagementservice.model.Patient;
import com.hsl.tokenmanagementservice.service.PatientService;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping("/add")
    public ResponseEntity<?> addPatient(@RequestBody PostPatientDTO patientDTO) {
        try {
            Patient patient = patientService.createPatient(patientDTO);
            return ResponseEntity.status(HttpStatus.OK).body(patient);
        }
        catch(EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        catch(BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
