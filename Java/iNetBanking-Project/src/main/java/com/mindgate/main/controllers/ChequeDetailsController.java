	package com.mindgate.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.main.domain.AccountDetails;
import com.mindgate.main.domain.ChequeDetails;
import com.mindgate.main.service.ChequeDetailsServiceInterface;

@RestController
@RequestMapping("chequedetails")
@CrossOrigin("http://localhost/4200")
public class ChequeDetailsController {
	@Autowired
	private ChequeDetailsServiceInterface chequeDetailsServiceInterface;
	@PutMapping("create-cheque")
	public boolean AddChequeDetail(@RequestBody ChequeDetails chequeDetails) {
		boolean result=chequeDetailsServiceInterface.AddChequeDetails(chequeDetails);
		return result;
	}
	
	@GetMapping("get-cheque/{chequeNumber}")
	public ResponseEntity<?> getChequeDetail(@PathVariable String chequeNumber){
		return chequeDetailsServiceInterface.getChequeDetails(chequeNumber);
	}

}
