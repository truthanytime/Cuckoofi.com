package com.cuckoofi.commonclientlibs.service;

import com.cuckoofi.commonclientlibs.entities.User;

public interface UserDetailsService extends org.springframework.security.core.userdetails.UserDetailsService {

    public void createVerificationTokenForUser(String token, User user);
    public void createVerificationTokenForForgotPassword(String token, String email);
    public String validateVerificationToken(String token);
}
