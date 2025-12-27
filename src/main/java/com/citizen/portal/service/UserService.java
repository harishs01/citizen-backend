package com.citizen.portal.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.citizen.portal.model.User;
import com.citizen.portal.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public User register(User user) {
        return repo.save(user);
    }

    public User login(String email, String password) {
        User user = repo.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
