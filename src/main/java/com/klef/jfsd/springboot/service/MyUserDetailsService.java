package com.klef.jfsd.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.UserPrincipal;
import com.klef.jfsd.springboot.model.Users;
import com.klef.jfsd.springboot.repo.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Users user = userRepo.findByEmail(email);
		
		if(user==null) {
			System.out.println("User Not Found");
			throw new UsernameNotFoundException("user not found"); 
		}
		
		return new UserPrincipal(user);
	}

}
