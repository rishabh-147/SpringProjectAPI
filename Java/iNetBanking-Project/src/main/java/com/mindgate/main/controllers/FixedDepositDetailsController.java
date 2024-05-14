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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.main.domain.AccountDetails;
import com.mindgate.main.domain.FixedDepositDetails;
import com.mindgate.main.service.FixedDepositDetailsServiceInterface;
import com.mindgate.main.service.UserDetailsServiceInterface;

@RestController
@RequestMapping("fddetails")
@CrossOrigin("http://localhost:4200")
public class FixedDepositDetailsController {
	
	@Autowired
	private FixedDepositDetailsServiceInterface fixedDepositDetailsService;

	
	@PostMapping("create-fd")
	public ResponseEntity<?> AddFixedDetail(@RequestBody FixedDepositDetails fixedDepositDetails) {
		 
		return fixedDepositDetailsService.addFixedDeposit(fixedDepositDetails);
	}

	@GetMapping("getAll/{userId}")
	public ResponseEntity<?> getFd(@PathVariable("userId")int userId){
		return fixedDepositDetailsService.getFd(userId);
	}
}
