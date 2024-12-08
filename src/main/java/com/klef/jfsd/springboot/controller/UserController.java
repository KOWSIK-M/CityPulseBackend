package com.klef.jfsd.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klef.jfsd.springboot.model.Users;
import com.klef.jfsd.springboot.repo.UserRepository;
import com.klef.jfsd.springboot.service.CloudinaryService;
import com.klef.jfsd.springboot.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {"https://citypulse-kowsik.vercel.app", "http://localhost:3000"})
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("add")
	public Map<String, String> addUser(@RequestBody Users user) {
		return userService.register(user);
	}

    @PostMapping("/updatePhoto")
    public ResponseEntity<?> updatePhoto(@RequestParam String email, @RequestParam MultipartFile file) {
        Users user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        try {
            String photoUrl = cloudinaryService.uploadFile(file);
            user.setPhotoUrl(photoUrl);
            userRepository.save(user);
            return ResponseEntity.ok("Photo updated successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload photo.");
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users user) {
        Users registeredUser = userService.securityRegister(user);
        if (registeredUser != null) {
            return ResponseEntity.ok(Map.of("success", "User registered successfully"));
        } else {
            return ResponseEntity.status(400).body(Map.of("error", "Registration failed"));
        }
    }

	
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        try {
            Map<String, Object> response = userService.verify(user);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
    
    @PutMapping("/updateProfile")
    public ResponseEntity<?> updateProfile(
        @RequestParam("user") String userJson,
        @RequestParam(value = "profileImage", required = false) MultipartFile profileImage
    ) {
        try {
            // Convert JSON string to User object
            ObjectMapper mapper = new ObjectMapper();
            Users user = mapper.readValue(userJson, Users.class);

            Users updatedUser = userService.updateProfile(user, profileImage);  // Call the service with the image
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody Users user) {
        try {
            String response = userService.updatePassword(user);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
