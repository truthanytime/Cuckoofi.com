package com.cuckoofi.commonclientlibs.repository;

import com.cuckoofi.commonclientlibs.entities.User;
import com.cuckoofi.commonclientlibs.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findByUser(User user);
}
