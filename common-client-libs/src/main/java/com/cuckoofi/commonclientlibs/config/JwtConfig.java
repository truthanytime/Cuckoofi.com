package com.cuckoofi.commonclientlibs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConfig {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private Integer jwtExpiration;

    public Integer getJwtExpiration() {
        return jwtExpiration;
    }

    public void setJwtExpiration(Integer jwtExpiration) {
        this.jwtExpiration = jwtExpiration;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }

    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }
}
