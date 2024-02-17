package com.hsl.tokenmanagementservice.util;

import com.hsl.tokenmanagementservice.model.GenderType;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DataValidationUtil {
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("[7-9][0-9]{9}");
    }

    public static boolean isValidBirthDate(String dob) {
        try {
            LocalDate.parse(dob);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isValidGender(String genderStr) {
        try {
            GenderType.valueOf(genderStr.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
