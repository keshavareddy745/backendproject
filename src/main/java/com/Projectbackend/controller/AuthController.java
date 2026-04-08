package com.Projectbackend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Projectbackend.model.User;
import com.Projectbackend.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User savedUser = service.registerUser(user);

        if (savedUser == null) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        User user = service.loginUser(email, password);

        if (user == null) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }

        if (!user.isApproved() && !"ADMIN".equalsIgnoreCase(user.getRole())) {
            return ResponseEntity.badRequest().body("Your account is pending admin approval");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("name", user.getName());
        response.put("email", user.getEmail());
        response.put("role", user.getRole());
        response.put("approved", user.isApproved());

        return ResponseEntity.ok(response);
    }
}