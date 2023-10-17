package com.cuckoofi.commonclientlibs.service.impl;

import com.cuckoofi.commonclientlibs.entities.User;
import com.cuckoofi.commonclientlibs.repository.UserRepository;
import com.cuckoofi.commonclientlibs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        Optional<User> candidate = userRepository.findByEmail( email );
        return candidate.orElse(null);
    }

    @Override
    public User saveUser( User user ) {
        return userRepository.save( user );
    }
}
