package com.cuckoofi.authservice.model;

import com.cuckoofi.commonclientlibs.constant.AuthConstant;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class SignInRequestBody implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = AuthConstant.REQUIRED_EMAIL)
    @JsonProperty("email")
    private String email;

    @NotNull(message = AuthConstant.REQUIRED_PASSWORD)
    @JsonProperty("password")
    private String password;
}