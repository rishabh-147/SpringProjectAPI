package com.mindgate.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.main.domain.AccountDetails;
import com.mindgate.main.domain.ChequeDetails;
import com.mindgate.main.domain.UserDetails;
import com.mindgate.main.service.AdminServiceInterface;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("admin-controller")
public class AdminController {
	
	@Autowired
	private AdminServiceInterface adminService;
	
	@GetMapping("get-inactive-user")
	public ResponseEntity<List<UserDetails>> getAllInactiveUsers(){
		return adminService.getAllInactiveUsers();
		
	}
	
	@GetMapping("get-locked-user")
	public ResponseEntity<List<UserDetails>> getAllLockedUsers(){
		return adminService.getAllLockedUsers();
		
	}
	
	@GetMapping("get-uncleared-cheque")
	public ResponseEntity<List<ChequeDetails>> getAllUnclearedCheques(){
		return adminService.getAllUnclearedCheques();
	}
	
	/*
	 * @GetMapping("get-seized-account") public ResponseEntity<List<AccountDetails>>
	 * getAllSeizedAccount(){ return adminService.getAllSeizedAccount(); }
	 */
	
	
	//update inactive user active of user
	
	@PutMapping("activate-user/{userId}")
	public ResponseEntity<?> activateUser(@PathVariable("userId") int userId){
		return adminService.activateUser(userId);
	}
	
	//update cheque status -- use verify account balance

	
}
