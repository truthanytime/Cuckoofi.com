package com.cuckoofi.authservice.service;

import com.cuckoofi.authservice.model.SignupRequestBody;
import com.cuckoofi.commonclientlibs.entities.User;

public interface UserManageService {

    User registerNewUser(SignupRequestBody signupRequestBody );
}
