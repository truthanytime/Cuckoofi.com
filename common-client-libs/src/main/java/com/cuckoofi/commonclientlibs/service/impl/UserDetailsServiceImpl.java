package com.cuckoofi.commonclientlibs.service.impl;

import com.cuckoofi.commonclientlibs.constant.ResponseConstant;
import com.cuckoofi.commonclientlibs.entities.User;
import com.cuckoofi.commonclientlibs.entities.VerificationToken;
import com.cuckoofi.commonclientlibs.repository.UserRepository;
import com.cuckoofi.commonclientlibs.repository.VerificationTokenRepository;
import com.cuckoofi.commonclientlibs.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, com.cuckoofi.commonclientlibs.service.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    VerificationTokenRepository tokenRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if( userOptional.isEmpty()) {
            userOptional = userRepository.findById(email);
            if (userOptional.isEmpty())
                return null;
        }
        User user = userOptional.get();
        List<GrantedAuthority> authorities = user.getRoles().stream().map(
                        role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return org.springframework.security.core
                .userdetails
                .User
                .withUsername(email)
                .password(user.getPassword())
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    @Override
    public void createVerificationTokenForUser(String token, User user) {
        VerificationToken newToken = new VerificationToken(token, user);
        newToken.setId(CommonUtil.uuidGenerator());
        tokenRepository.save(newToken);
    }

    @Override
    public void createVerificationTokenForForgotPassword(String token, String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if( user.isPresent()) {
            VerificationToken newToken = new VerificationToken(token, user.get());
            newToken.setId(CommonUtil.uuidGenerator());
            tokenRepository.save(newToken);
        }
    }

    @Override
    public String validateVerificationToken(String token) {
        Optional<VerificationToken> verificationToken = tokenRepository.findById(token);

        if (verificationToken.isPresent()) {
            Calendar cal = Calendar.getInstance();
            if (verificationToken.get().getExpiryDate().compareTo(cal.getTime()) < 0) {
                tokenRepository.delete(verificationToken.get());
                return ResponseConstant.TOKEN_EXPIRED;
            }
            return ResponseConstant.TOKEN_VALID;
        } else {
            return ResponseConstant.TOKEN_INVALID;
        }
    }
}
