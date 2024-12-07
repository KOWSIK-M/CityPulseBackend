package com.klef.jfsd.springboot.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.Admin;
import com.klef.jfsd.springboot.repo.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private JWTService jwtService;

    @Override
    public Map<String, Object> verifyAdmin(Admin admin) {
        Admin existingAdmin = adminRepository.findByUsername(admin.getUsername());

        if (existingAdmin == null) {
            throw new RuntimeException("Admin not found with username: " + admin.getUsername());
        }

        if (!existingAdmin.getPassword().equals(admin.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Generate JWT Token
        String token = jwtService.generateToken(admin.getUsername());

        // Prepare response
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", existingAdmin);
        return response;
    }
}
