package com.mindgate.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.main.domain.AccountDetails;
import com.mindgate.main.service.AccountDetailsServiceInterface;

@RestController
@RequestMapping("accountdetails")
@CrossOrigin("http://localhost:4200/")
public class AccountDetailsController {
	@Autowired
	private AccountDetailsServiceInterface accountDetailsServiceInterface;

	@GetMapping("getbyid/{userId}")
	public ResponseEntity<?> getById(@PathVariable("userId") int userId) {
		try {
			ResponseEntity<List<AccountDetails>> responseEntity = new ResponseEntity<List<AccountDetails>>(
					accountDetailsServiceInterface.getById(userId), HttpStatusCode.valueOf(200));
			return responseEntity;
		} catch (Exception e) {
			return null;
		}

	}
	
	@GetMapping("get-acc-by-accNum/{accNum}")
	public ResponseEntity<?> getByAccNum(@PathVariable("accNum") long accountNumber){
		return accountDetailsServiceInterface.getByAccount(accountNumber);
	}

	// Use this when passing userId
	@PostMapping("addaccount")
	public ResponseEntity<?>  addAccount(@RequestBody AccountDetails accountDetails) {
		return accountDetailsServiceInterface.addAccount(accountDetails);
	}
	
	
	
	
	
}
