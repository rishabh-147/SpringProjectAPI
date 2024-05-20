package com.mindgate.main.service;

import java.util.List;

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
		boolean res = false;
		for(int i = 0; i < 5; i++) {
		res = chequeDetailsRepository.AddChequeDetails(chequeDetails);
		}
		return res;
	}
	
	@Override
	public ResponseEntity<ChequeDetails> getChequeDetails(String cheque_no) {
		
		return new ResponseEntity<ChequeDetails>(chequeDetailsRepository.getChequeDetails(cheque_no), HttpStatusCode.valueOf(200));
	}
	
	@Override
	public List<ChequeDetails> getByAccountNumber(int accountNumber) {
		// TODO Auto-generated method stub
		return chequeDetailsRepository.getByAccountNumber(accountNumber);
	}
	
	@Override
	public boolean updateCheque(ChequeDetails chequeDetails) {
		return chequeDetailsRepository.updateCheque(chequeDetails);
		
	}

}
