package com.mindgate.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.mindgate.main.domain.AccountDetails;
import com.mindgate.main.domain.ChequeDetails;
import com.mindgate.main.domain.TransactionDetails;
import com.mindgate.main.service.AccountDetailsServiceInterface;
import com.mindgate.main.service.ChequeDetailsServiceInterface;
import com.mindgate.main.service.TransactionDetailsServiceInterface;

@RestController
@RequestMapping("chequedetails")
@CrossOrigin("http://localhost:4200/")
public class ChequeDetailsController {
	@Autowired
	private ChequeDetailsServiceInterface chequeDetailsService;
	@Autowired
	private TransactionDetailsServiceInterface transactionDetailsService;
	@Autowired
	private AccountDetailsServiceInterface accountDetailsService;

	@PostMapping("create-cheque")
	public ResponseEntity<?> AddChequeDetail(@RequestBody ChequeDetails chequeDetails) {
		return chequeDetailsService.AddChequeDetails(chequeDetails);

	}

	@PutMapping("issue-cheque")
	public ResponseEntity<?> updateCheque(@RequestBody ChequeDetails chequeDetails) {
		return chequeDetailsService.updateCheque(chequeDetails);
	}

	@GetMapping("get-cheque/{chequeNumber}")
	public ResponseEntity<?> getChequeDetail(@PathVariable String chequeNumber) {
		return chequeDetailsService.getChequeDetails(chequeNumber);
	}

	@GetMapping("get-cheques-by-acc/{accountNumber}")
	public ResponseEntity<?> getchequebyaccount(@PathVariable int accountNumber) {
		return chequeDetailsService.getByAccountNumber(accountNumber);
	}

	@PostMapping("process-cheque")
	public ResponseEntity<Boolean> chequeProcess(@RequestBody ChequeDetails chequeDetails) {
		
		TransactionDetails details = new TransactionDetails();
		AccountDetails issuer = accountDetailsService.getByAccount(chequeDetails.getIssuerAccountNumber().getAccountNumber()).getBody();
		details.setIssuerAccountDetails(issuer);
		AccountDetails benificiary = accountDetailsService.getByAccount(chequeDetails.getBenificiaryAccountNumber().getAccountNumber()).getBody();
		details.setBenificiaryAccountDetails(benificiary);
		details.setTransactionAmount(chequeDetails.getChequeAmount());

		details.setRemarks("Cheque - " + chequeDetails.getChequeNumber());
		details.setTransactionType("debit");
		details.setTransactionMode("cheque");

		HttpStatusCode code =  transactionDetailsService.performTransaction(details).getStatusCode();
		if(code == HttpStatusCode.valueOf(200)) {
			return transactionDetailsService.performTransaction(details);
		}else{
			return transactionDetailsService.bouncedCheque(details);
		}

	}

	@PostMapping("get-by-date")
	public ResponseEntity<?> getbydate(@RequestBody ChequeDetails chequeDetails) {

		return chequeDetailsService.claimCheque(chequeDetails);
	}

	@PostMapping("getCheque")
	public ResponseEntity<?> getcheque(@RequestBody ChequeDetails chequeDetails) {
		return chequeDetailsService.updateCheque1(chequeDetails);
	}
	
	
	@GetMapping("has-cheque/{accNum}")
	public ResponseEntity<?> hasCheque(@PathVariable("accNum") int accNum){
		return chequeDetailsService.hasCheque(accNum);
	}
}
