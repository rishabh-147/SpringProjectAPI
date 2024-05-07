package com.mindgate.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.main.domain.UserDetails;
import com.mindgate.main.repository.UserDetailsServiceInterface;

@RestController
@RequestMapping("userdetails")
public class UserDetailsController {

	@Autowired
	private UserDetailsServiceInterface service;
	
	@GetMapping("getbyid/{emailId}")
	public UserDetails getById(@PathVariable("emailId") String emailId) {
		return service.getById(emailId);
	}
}