package com.citizen.portal.controller;

import org.springframework.web.bind.annotation.*;
import com.citizen.portal.model.User;
import com.citizen.portal.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return service.login(user.getEmail(), user.getPassword());
    }

    // 🔴 POST TEST
    @PostMapping("/test")
    public String testPost() {
        return "POST WORKING";
    }

    // 🟢 GET TEST
    @GetMapping("/test")
    public String testGet() {
        return "GET WORKING";
    }
}
