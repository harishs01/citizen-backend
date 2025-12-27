package com.citizen.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.citizen.portal.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    // used for login
    User findByEmail(String email);
}
