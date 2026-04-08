package com.Projectbackend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Projectbackend.model.User;
import com.Projectbackend.service.UserService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final UserService service;

    public AdminController(UserService service) {
        this.service = service;
    }

    @GetMapping("/pending-users")
    public ResponseEntity<List<User>> getPendingUsers() {
        return ResponseEntity.ok(service.getPendingUsers());
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<?> approveUser(@PathVariable Long id) {
        User approvedUser = service.approveUser(id);

        if (approvedUser == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        return ResponseEntity.ok(approvedUser);
    }
}