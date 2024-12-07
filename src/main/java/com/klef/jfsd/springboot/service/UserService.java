package com.klef.jfsd.springboot.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.klef.jfsd.springboot.model.Users;

public interface UserService {
	public Map<String,String> register(Users user);
	public Users securityRegister(Users user);
	public Map<String, Object> verify(Users user);
	public Users updateProfile(Users user, MultipartFile profileImage);
	public String updatePassword(Users user);
	public Long countUsers();
	public List<Users> getAllUsers();
}
