package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.User;
import com.tehnology.kkt.models.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface UserDAO extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findUserByRoles(Role role);

    List<User> findAllByName(String name);
}
