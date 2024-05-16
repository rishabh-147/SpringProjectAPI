package com.mindgate.main.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mindgate.main.domain.TransactionDetails;

public interface TransactionDetailsServiceInterface {
	public boolean addTransactionDetails(TransactionDetails transactionDetails);
	public ResponseEntity<List<TransactionDetails>> getbyIssuerAccId(long accNumber);
	ResponseEntity<Boolean> performTransaction(TransactionDetails details);
}
