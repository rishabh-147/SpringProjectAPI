	package com.mindgate.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.mindgate.main.domain.ChequeDetails;
import com.mindgate.main.service.ChequeDetailsServiceInterface;

@RestController
@RequestMapping("chequedetails")
@CrossOrigin("http://localhost:4200/")
public class ChequeDetailsController {
	@Autowired
	private ChequeDetailsServiceInterface chequeDetailsService;
	
	@PostMapping("create-cheque")
	public boolean AddChequeDetail(@RequestBody ChequeDetails chequeDetails) {
		boolean result=chequeDetailsService.AddChequeDetails(chequeDetails);
		return result;
	}
	
	@PutMapping("issue-cheque")
	public boolean updateCheque(@RequestBody ChequeDetails chequeDetails) {
		return chequeDetailsService.updateCheque(chequeDetails);
	}
	
	@GetMapping("get-cheque/{chequeNumber}")
	public ResponseEntity<?> getChequeDetail(@PathVariable String chequeNumber){
		return chequeDetailsService.getChequeDetails(chequeNumber);
	}
	@GetMapping("get-cheques-by-acc/{accountNumber}")
    public List<ChequeDetails> getchequebyaccount(@PathVariable int accountNumber){
        return chequeDetailsService.getByAccountNumber(accountNumber);
    }

}
