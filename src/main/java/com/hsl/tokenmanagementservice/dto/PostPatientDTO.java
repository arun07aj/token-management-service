package com.hsl.tokenmanagementservice.dto;

public class PostPatientDTO {
    private Long id;
    private String name;
    private boolean isExistingPatient;
    private String opNumber;
    private String dateOfBirth;
    private String gender;
    private String phoneNumber;

    public PostPatientDTO(Long id, String name, boolean isExistingPatient, String opNumber, String dateOfBirth, String gender, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.isExistingPatient = isExistingPatient;
        this.opNumber = opNumber;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isExistingPatient() {
        return isExistingPatient;
    }

    public void setExistingPatient(boolean existingPatient) {
        isExistingPatient = existingPatient;
    }

    public String getOpNumber() {
        return opNumber;
    }

    public void setOpNumber(String opNumber) {
        this.opNumber = opNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
