package com.mindgate.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.main.domain.FixedDepositDetails;
import com.mindgate.main.domain.TransactionDetails;
import com.mindgate.main.service.FixedDepositDetailsServiceInterface;
import com.mindgate.main.service.TransactionDetailsServiceInterface;

@RestController
@RequestMapping("transactionsdetails")
public class TransactionDetailsController {

	@Autowired
	private TransactionDetailsServiceInterface transactionDetailsServiceInterface;
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public boolean AddFixedDetail(@RequestBody TransactionDetails transactionDetails) {
		boolean result =transactionDetailsServiceInterface.addTransactionDetails(transactionDetails);
		return result;
	}
}
