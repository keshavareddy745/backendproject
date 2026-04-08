package com.Projectbackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Projectbackend.model.User;
import com.Projectbackend.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User registerUser(User user) {
        Optional<User> existingUser = repository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return null;
        }

        if ("ADMIN".equalsIgnoreCase(user.getRole())) {
            user.setApproved(true);
        } else {
            user.setApproved(false);
        }

        return repository.save(user);
    }

    public User loginUser(String email, String password) {
        Optional<User> user = repository.findByEmail(email);

        if (user.isPresent()) {
            User existingUser = user.get();

            if (existingUser.getPassword().equals(password)) {
                return existingUser;
            }
        }

        return null;
    }

    public List<User> getPendingUsers() {
        return repository.findByApproved(false);
    }

    public User approveUser(Long id) {
        Optional<User> user = repository.findById(id);

        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setApproved(true);
            return repository.save(existingUser);
        }

        return null;
    }
}