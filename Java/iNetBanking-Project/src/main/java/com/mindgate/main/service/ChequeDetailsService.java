package com.mindgate.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.ChequeDetails;
import com.mindgate.main.repository.ChequeDetailsRepositoryInterface;
@Service
public class ChequeDetailsService implements ChequeDetailsServiceInterface {
	@Autowired
	private ChequeDetailsRepositoryInterface chequeDetailsRepository;
	@Override
	public boolean AddChequeDetails(ChequeDetails chequeDetails) {
		
		return chequeDetailsRepository.AddChequeDetails(chequeDetails);
	}
	@Override
	public ResponseEntity<ChequeDetails> getChequeDetails(String cheque_no) {
		
		return new ResponseEntity<ChequeDetails>(chequeDetailsRepository.getChequeDetails(cheque_no), HttpStatusCode.valueOf(200));
	}

}
