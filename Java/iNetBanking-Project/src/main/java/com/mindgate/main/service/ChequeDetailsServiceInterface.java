package com.mindgate.main.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mindgate.main.domain.ChequeDetails;

public interface ChequeDetailsServiceInterface {
	public ResponseEntity<Boolean> AddChequeDetails(ChequeDetails chequeDetails);
	public ResponseEntity<ChequeDetails> getChequeDetails(String chequeDetails);
	public ResponseEntity<List<ChequeDetails>> getByAccountNumber(int accountNumber);
	public ResponseEntity<?> updateCheque(ChequeDetails chequeDetails) ;
}
