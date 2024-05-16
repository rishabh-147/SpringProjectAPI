package com.mindgate.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.main.domain.TransactionDetails;
import com.mindgate.main.service.TransactionDetailsServiceInterface;

@RestController
@RequestMapping("transactionsdetails")
@CrossOrigin("http://localhost:4200/")
public class TransactionDetailsController {

	@Autowired
	private TransactionDetailsServiceInterface transactionDetailsService;
	
	
	@PostMapping("add-details")
	public boolean AddFixedDetail(@RequestBody TransactionDetails transactionDetails) {
		boolean result =transactionDetailsService.addTransactionDetails(transactionDetails);
		return result;
	}
	
	@GetMapping("getall/{accNumber}")
	public ResponseEntity<List<TransactionDetails>> getAllIssuerAcc(@PathVariable long accNumber){
		return transactionDetailsService.getbyIssuerAccId(accNumber);
	}
	
	@PostMapping("process-transaction")
	public ResponseEntity<?> processTransaction(@RequestBody TransactionDetails transactionDetails){
		return transactionDetailsService.performTransaction(transactionDetails);
	}
}
