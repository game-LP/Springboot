package com.example.mytest.controller;

import com.example.mytest.model.User;
import com.example.mytest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/profiles")
public class UserController {

    @Autowired
    private UserService userService;

    // GET /api/v1/profiles/
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllProfiles() {
        return ResponseEntity.ok(userService.getAllProfiles());
    }

    // GET /api/v1/profiles/:uuid
    @GetMapping("/{uuid}")
    public ResponseEntity<User> getProfileById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(userService.getProfileById(uuid));
    }

    // POST /api/v1/profiles/
    @PostMapping("/")
    public ResponseEntity<User> createProfile(@RequestBody UserRequest request) {
        User user = userService.createProfile(request.getName(), request.getEmail());
        return ResponseEntity.ok(user);
    }

    // PUT /api/v1/profiles/:uuid
    @PutMapping("/{uuid}")
    public ResponseEntity<User> updateProfile(@PathVariable UUID uuid, @RequestBody UserRequest request) {
        User updatedUser = userService.updateProfile(uuid, request.getName());
        return ResponseEntity.ok(updatedUser);
    }

    // DELETE /api/v1/profiles/:uuid
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteProfile(@PathVariable UUID uuid) {
        userService.deleteProfile(uuid);
        return ResponseEntity.noContent().build();
    }
}

// Simple DTO for request bodies
class UserRequest {
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}