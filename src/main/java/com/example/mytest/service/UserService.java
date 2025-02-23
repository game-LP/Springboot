package com.example.mytest.service;

import com.example.mytest.model.User;
import com.example.mytest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Get all profiles
    public List<User> getAllProfiles() {
        return userRepository.findAll();
    }

    // Get a profile by UUID
    public User getProfileById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found with id: " + id));
    }

    // Create a new profile
    public User createProfile(String name, String email) {
        User user = new User(name, email);
        return userRepository.save(user);
    }

    // Update a profile
    public User updateProfile(UUID id, String name) {
        User user = getProfileById(id);
        user.setName(name);
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    // Delete a profile
    public void deleteProfile(UUID id) {
        User user = getProfileById(id);
        userRepository.delete(user);
    }
}
