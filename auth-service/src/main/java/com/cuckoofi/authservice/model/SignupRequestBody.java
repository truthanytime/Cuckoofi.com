package com.cuckoofi.authservice.model;

import com.cuckoofi.commonclientlibs.constant.AuthConstant;
import com.cuckoofi.commonclientlibs.exceptions.ValidTimeZone;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class SignupRequestBody implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = AuthConstant.REQUIRED_EMAIL)
    @Email(message = AuthConstant.INVALID_EMAIL)
    @JsonProperty("email")
    private String email;

    @NotNull(message = AuthConstant.REQUIRED_FIRST_NAME)
    @JsonProperty("firstName")
    private String firstName;

    @NotNull(message = AuthConstant.REQUIRED_LAST_NAME)
    @JsonProperty("lastName")
    private String lastName;

    @NotNull(message = AuthConstant.REQUIRED_PASSWORD)
    @JsonProperty("password")
    private String password;

    @ValidTimeZone()
    @JsonProperty("timeZone")
    private String timeZone = AuthConstant.DEFAULT_TIMEZONE;
}
