package com.mindgate.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.main.domain.UserDetails;
import com.mindgate.main.service.UserDetailsServiceInterface;

@RestController
@RequestMapping("userdetails")
@CrossOrigin("http://localhost:4200/")
public class UserDetailsController {

	@Autowired
	private UserDetailsServiceInterface service;
	
	@GetMapping("getbyid/{emailId}")
	public ResponseEntity<?> getById(@PathVariable("emailId") String emailId) {
		return service.getById(emailId);

	}
	
	@GetMapping("getbyuserid/{userId}")
	public ResponseEntity<?> getByUserId(@PathVariable("userId") int userId) {
		return service.getByUserId(userId);

	}
	
	@PostMapping("verifylogin")
	public ResponseEntity<?> loginVerification(@RequestBody UserDetails userDetails) {
		return service.verifylogin(userDetails);
	}
	
	@PostMapping("adminlogin")
	public ResponseEntity<?> adminloginverfication(@RequestBody UserDetails userDetails){
		return service.verifyadminlogin(userDetails);
	}
	
	@PostMapping("adduser")
	public ResponseEntity<?> addUserDetails(@RequestBody UserDetails userDetails){
		//System.out.println(userDetails);
		return service.addUser(userDetails);
	}
	@PostMapping("existinguser")
    public boolean addexist(@RequestBody UserDetails userDetails) {
        return service.existDetail(userDetails);
    }
}
