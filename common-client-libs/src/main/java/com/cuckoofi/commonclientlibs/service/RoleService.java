package com.cuckoofi.commonclientlibs.service;

import com.cuckoofi.commonclientlibs.entities.Role;
import com.cuckoofi.commonclientlibs.entities.User;

import java.util.Set;

public interface RoleService {

    Set<Role> findByUser(User user);
}
