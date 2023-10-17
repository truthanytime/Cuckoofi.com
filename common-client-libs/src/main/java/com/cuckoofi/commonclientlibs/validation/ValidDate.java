package com.cuckoofi.commonclientlibs.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDate {
    String message() default "Invalid date format. Use YYYY-MM-DD or MM/DD/YYYY.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
