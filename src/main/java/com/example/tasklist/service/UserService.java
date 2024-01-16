package com.example.tasklist.service;

import com.example.tasklist.model.User;
import com.example.tasklist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        //removed , PasswordEncoder passwordEncoder from passed params
        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
    }

    public boolean registerUser(User user) {
        // Check if username already exists
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            // Username already exists
            return false;
        }
        // Encrypt the password before saving
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPassword(user.getPassword());

        // Save the new user
        userRepository.save(user);
        return true;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean loginUser(String username, String password) {
        Optional<User> existingUser = userRepository.findByUsername(username);

        if (existingUser.isPresent()) {
            // Compare the provided password with the stored hashed password
            User user = existingUser.get();
//            return passwordEncoder.matches(password, user.getPassword());
            return password.matches(user.getPassword());
        }

        // Username not found or password does not match
        return false;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Additional methods as needed...
}
