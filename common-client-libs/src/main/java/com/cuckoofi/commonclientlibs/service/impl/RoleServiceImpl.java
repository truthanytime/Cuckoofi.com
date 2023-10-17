package com.cuckoofi.commonclientlibs.service.impl;

import com.cuckoofi.commonclientlibs.entities.Role;
import com.cuckoofi.commonclientlibs.entities.User;
import com.cuckoofi.commonclientlibs.entities.UserRole;
import com.cuckoofi.commonclientlibs.repository.RoleRepository;
import com.cuckoofi.commonclientlibs.repository.UserRepository;
import com.cuckoofi.commonclientlibs.repository.UserRoleRepository;
import com.cuckoofi.commonclientlibs.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public Set<Role> findByUser(User user) {
        List<UserRole> userRoles = userRoleRepository.findByUser( user );
        return userRoles.stream().map(UserRole::getRole).collect(Collectors.toSet());
    }
}
