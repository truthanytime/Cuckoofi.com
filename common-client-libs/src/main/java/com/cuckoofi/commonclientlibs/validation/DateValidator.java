package com.cuckoofi.commonclientlibs.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateValidator implements ConstraintValidator<ValidDate, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Define the allowed date formats
        DateTimeFormatter[] allowedFormats = {
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("MM/dd/yyyy")
        };

        for (DateTimeFormatter format : allowedFormats) {
            try {
                LocalDate.parse(value, format);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        return false;
    }
}

