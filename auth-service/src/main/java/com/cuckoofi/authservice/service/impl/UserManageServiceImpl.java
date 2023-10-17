package com.cuckoofi.authservice.service.impl;

import com.cuckoofi.authservice.model.SignupRequestBody;
import com.cuckoofi.authservice.service.UserManageService;
import com.cuckoofi.commonclientlibs.constant.AuthConstant;
import com.cuckoofi.commonclientlibs.entities.Role;
import com.cuckoofi.commonclientlibs.entities.User;
import com.cuckoofi.commonclientlibs.entities.UserRole;
import com.cuckoofi.commonclientlibs.repository.RoleRepository;
import com.cuckoofi.commonclientlibs.repository.UserRepository;
import com.cuckoofi.commonclientlibs.repository.UserRoleRepository;
import com.cuckoofi.commonclientlibs.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserManageServiceImpl implements UserManageService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public User registerNewUser(SignupRequestBody signupRequestBody) {

        // Save new user.
        User user = new User();
        user.setId(CommonUtil.uuidGenerator());
        user.setEmail(signupRequestBody.getEmail());
        user.setTimeZone(signupRequestBody.getTimeZone());
        user.setFirstName(signupRequestBody.getFirstName());
        user.setLastName(signupRequestBody.getLastName());
        user.setVerified(AuthConstant.EMAIL_VERIFY.NOT_VERIFIED);
        String encryptedPassword = encoder().encode(signupRequestBody.getPassword());
        user.setPassword(encryptedPassword);
        user = userRepository.save(user);

        // Get default role
        Role defaultRole;
        Optional<Role> candidate = roleRepository.findByName( AuthConstant.ROLES.USER );
        if(candidate.isEmpty()){
            defaultRole = new Role();
            defaultRole.setName( AuthConstant.ROLES.USER );
            defaultRole = roleRepository.save( defaultRole );
        }
        else{
            defaultRole = candidate.get();
        }

        // Assign default role to new registered user.
        UserRole newUserRole = new UserRole();
        newUserRole.setUser( user );
        newUserRole.setRole( defaultRole );
        userRoleRepository.save( newUserRole );

        return user;
    }
}
