package com.hsl.tokenmanagementservice.service;

import com.hsl.tokenmanagementservice.dto.PostPatientDTO;
import com.hsl.tokenmanagementservice.exception.BadRequestException;
import com.hsl.tokenmanagementservice.model.GenderType;
import com.hsl.tokenmanagementservice.model.Patient;
import com.hsl.tokenmanagementservice.repository.PatientRepository;
import com.hsl.tokenmanagementservice.util.LoggerUtil;
import jakarta.persistence.EntityExistsException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;

import static com.hsl.tokenmanagementservice.util.DataValidationUtil.isValidBirthDate;
import static com.hsl.tokenmanagementservice.util.DataValidationUtil.isValidGender;
import static com.hsl.tokenmanagementservice.util.DataValidationUtil.isValidPhoneNumber;

@Service
public class PatientService {
    private static final Logger logger = LoggerUtil.getLogger(PatientService.class);

    @Autowired
    private PatientRepository patientRepository;
    public Patient createPatient(PostPatientDTO patientDTO) {
        Patient patient = null;
        if(patientDTO != null) {
            patient = validateAndSetPatient(patientDTO);
            if(patient.getOpNumber() == null) {
                patient.setOpNumber(generateOPTicketNumber(patient.getName()));
                System.out.println(patient.getOpNumber());
            }

            if(patient.getOpNumber() != null && !patientRepository.findPatientByOpNumber(patient.getOpNumber()).isPresent()) {
                patientRepository.save(patient);
                logger.info("patient details saved to repo successfully");
            }
            else {
                throw new EntityExistsException("patient already exists with the given OP number");
            }
        }
        return patient;
    }

    private Patient validateAndSetPatient(PostPatientDTO patientDTO) {
        Patient patient = new Patient();

        // Patient's name validation
        if(patientDTO.getName() != null && !patientDTO.getName().isEmpty()){
            patient.setName(patientDTO.getName());
        }
        else {
            throw new BadRequestException("patient name cannot be empty");
        }

        // Patient's DOB validation
        if(isValidBirthDate(patientDTO.getDateOfBirth())) {
            patient.setDateOfBirth(patientDTO.getDateOfBirth());
        }
        else {
            throw new BadRequestException("invalid format for birth date");
        }

        // Patient's gender validation
        if(isValidGender(patientDTO.getGender())) {
            patient.setGender(GenderType.valueOf(patientDTO.getGender().toUpperCase()));
        }
        else {
            throw new BadRequestException("invalid gender type");
        }

        // Patient's phone number validation
        if(isValidPhoneNumber(patientDTO.getPhoneNumber())) {
            patient.setPhoneNumber(patientDTO.getPhoneNumber());
        }
        else {
            throw new BadRequestException("invalid phone number");
        }

        // Set OP registration number if existingPatient flag is enabled
        if(patientDTO.isExistingPatient()) {
            if(patientDTO.getOpNumber() != null && !patientDTO.getOpNumber().isEmpty()) {
                patient.setOpNumber(patientDTO.getOpNumber());
            }
            else {
                throw new BadRequestException("patient is existing but no OP number was provided");
            }
        }
        logger.info("patient dto validation completed successfully");
        return patient;
    }

    private String generateOPTicketNumber(String patientName) {
        // Extract first two characters of the patient's name (assuming the name has at least two characters)
        String nameInitials = patientName.substring(0, Math.min(patientName.length(), 2)).toUpperCase();

        // Get the current time in milliseconds
        long currentTimeMillis = Instant.now().toEpochMilli();
        // Extract the last four digits of the current time
        String timeSuffix = String.valueOf(currentTimeMillis).substring(Math.max(String.valueOf(currentTimeMillis).length() - 4, 0));

        // Generate a random number between 1000 and 9999
        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000;

        // Format the ticket number using patient's initials, current time suffix, and random number
        return nameInitials + timeSuffix + randomNumber;
    }

}
