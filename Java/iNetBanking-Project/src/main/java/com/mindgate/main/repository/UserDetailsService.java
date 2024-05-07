package com.mindgate.main.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.UserDetails;

@Service
public class UserDetailsService implements UserDetailsServiceInterface{
	
	@Autowired
	private UserDetailsRepoInterface userDetailsRepo;

	@Override
	public UserDetails getById(String emailId) {
		
		return userDetailsRepo.getById(emailId);
	}

}
