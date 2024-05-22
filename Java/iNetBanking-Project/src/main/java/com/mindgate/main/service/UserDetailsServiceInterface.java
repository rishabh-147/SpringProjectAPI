package com.mindgate.main.service;

import org.springframework.http.ResponseEntity;

import com.mindgate.main.domain.UserDetails;

public interface UserDetailsServiceInterface {
	public ResponseEntity<?> getById(String emailId);
	
	public ResponseEntity<?> getByUserId(int userId);
	//Verification
	public ResponseEntity<?> verifylogin(UserDetails userDetails);
	//admin login service
	public ResponseEntity<?> verifyadminlogin(UserDetails userDetails);
	
	//Insert
	public ResponseEntity<?> addUser(UserDetails userDetails);
	public boolean existDetail(UserDetails userdetails);

}
