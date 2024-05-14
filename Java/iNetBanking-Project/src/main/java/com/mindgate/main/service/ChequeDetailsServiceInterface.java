package com.mindgate.main.service;

import org.springframework.http.ResponseEntity;

import com.mindgate.main.domain.ChequeDetails;

public interface ChequeDetailsServiceInterface {
	public boolean AddChequeDetails(ChequeDetails chequeDetails);
	public ResponseEntity<ChequeDetails> getChequeDetails(String chequeDetails);
}
