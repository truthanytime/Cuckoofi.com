package com.cuckoofi.commonclientlibs.exceptions;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.ZoneId;
import java.time.zone.ZoneRulesException;

public class ValidTimeZoneValidator implements ConstraintValidator<ValidTimeZone, String> {
    @Override
    public void initialize(ValidTimeZone constraintAnnotation) {
    }

    @Override
    public boolean isValid(String timeZone, ConstraintValidatorContext context) {
        if (timeZone == null || timeZone.trim().isEmpty()) {
            return false;
        }

        try {
            ZoneId zoneId = ZoneId.of(timeZone);
            return true;
        } catch (ZoneRulesException e) {
            return false;
        }
    }
}
