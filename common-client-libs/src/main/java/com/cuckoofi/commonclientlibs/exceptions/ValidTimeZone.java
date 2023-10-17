package com.cuckoofi.commonclientlibs.exceptions;

import com.cuckoofi.commonclientlibs.constant.AuthConstant;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidTimeZoneValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTimeZone {
    String message() default AuthConstant.REQUIRED_VALID_TIMEZONE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
