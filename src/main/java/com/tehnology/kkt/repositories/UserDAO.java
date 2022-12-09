package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Long> {
    User findByEmail(String email);

}
