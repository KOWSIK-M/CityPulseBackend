package com.klef.jfsd.springboot.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.klef.jfsd.springboot.model.Users;
import com.klef.jfsd.springboot.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Map<String,String> register(Users user) {
		Map<String,String> response = new HashMap<String, String>();
		if(userRepository.existsByEmail(user.getEmail())) {
			response.put("success","User with Email "+user.getEmail()+" already exists");
			return response;
		}
		userRepository.save(user);
	    response.put("success","User Saved SuccessFully");
		return response;
	}
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	public Users securityRegister(Users user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public Map<String, Object> verify(Users user) {
	    Authentication authentication = authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
	    );

	    if (authentication.isAuthenticated()) {
	        String token = jwtService.generateToken(user.getEmail());
	        Users authenticatedUser = userRepository.findByEmail(user.getEmail());

	        Map<String, Object> response = new HashMap<>();
	        response.put("token", token);
	        response.put("user", authenticatedUser); // Include user details
	        return response;
	    } else {
	        throw new RuntimeException("Authentication failed");
	    }
	}
	

	@Autowired
	private CloudinaryService cloudinaryService;

	@Override
	public Users updateProfile(Users user, MultipartFile profileImage) {
	    Users existingUser = userRepository.findByEmail(user.getEmail());
	    if (existingUser == null) {
	        throw new RuntimeException("User not found");
	    }

	    // Update fields
	    existingUser.setUsername(user.getUsername());
	    existingUser.setDob(user.getDob());
	    existingUser.setCity(user.getCity());
	    existingUser.setContact(user.getContact());

	    // Upload new profile image to Cloudinary
	    if (profileImage != null && !profileImage.isEmpty()) {
	        try {
	            String photoUrl = cloudinaryService.uploadFile(profileImage); // Upload image and get URL
	            existingUser.setPhotoUrl(photoUrl); // Save photo URL to database
	        } catch (IOException e) {
	            throw new RuntimeException("Failed to upload profile image", e);
	        }
	    }

	    return userRepository.save(existingUser);
	}


	
    @Override
    public String updatePassword(Users user) {
        Users existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser == null) {
            throw new RuntimeException("User not found with email: " + user.getEmail());
        }

        existingUser.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(existingUser);

        return "Password updated successfully. A confirmation email has been sent.";
    }

    public Long countUsers() {
    	return userRepository.count();
    }
    
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }
}
