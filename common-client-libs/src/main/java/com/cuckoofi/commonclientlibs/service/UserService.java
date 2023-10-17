package com.cuckoofi.commonclientlibs.service;

import com.cuckoofi.commonclientlibs.entities.User;

public interface UserService {
    User findByEmail(String email);
    User saveUser(User user);
}
