package com.mindgate.main.service;

import org.springframework.http.ResponseEntity;

import com.mindgate.main.domain.UserDetails;

public interface UserDetailsServiceInterface {
	public ResponseEntity<?> getById(String emailId);
	//Verification
	public ResponseEntity<?> verifylogin(UserDetails userDetails);
}
