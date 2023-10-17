package com.cuckoofi.authservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthResponse implements Serializable {

    private String id;
    private String email;
    private Integer verified;
    private String authToken;
    private String firstName;
    private String lastName;
    private String timeZone;

    public AuthResponse(
            String id,
            String email,
            Integer verified,
            String token,
            String firstName,
            String lastName,
            String timeZone) {
        super();
        this.id = id;
        this.email = email;
        this.verified = verified;
        this.authToken = token;
        this.firstName = firstName;
        this.lastName = lastName;
        this.timeZone = timeZone;
    }
}
