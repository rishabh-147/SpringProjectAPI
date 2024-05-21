package com.mindgate.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.AccountDetails;
import com.mindgate.main.domain.ChequeDetails;
import com.mindgate.main.repository.ChequeDetailsRepositoryInterface;

@Service
public class ChequeDetailsService implements ChequeDetailsServiceInterface {
	@Autowired
	private ChequeDetailsRepositoryInterface chequeDetailsRepository;

	@Override
	public ResponseEntity<Boolean> AddChequeDetails(ChequeDetails chequeDetails) {
		boolean res = false;
		for (int i = 0; i < 5; i++) {
			res = chequeDetailsRepository.AddChequeDetails(chequeDetails);
		}
		return new ResponseEntity<Boolean>(res, HttpStatusCode.valueOf(200));
	}

	@Override
	public ResponseEntity<ChequeDetails> getChequeDetails(String cheque_no) {

		return new ResponseEntity<ChequeDetails>(chequeDetailsRepository.getChequeDetails(cheque_no),
				HttpStatusCode.valueOf(200));
	}

	@Override
	public ResponseEntity<List<ChequeDetails>> getByAccountNumber(int accountNumber) {
		// TODO Auto-generated method stub
		return new ResponseEntity<List<ChequeDetails>>(chequeDetailsRepository.getByAccountNumber(accountNumber),
				HttpStatusCode.valueOf(200));
	}

	@Override
	public ResponseEntity<Boolean> updateCheque(ChequeDetails chequeDetails) {
		return new ResponseEntity<Boolean>(chequeDetailsRepository.updateCheque(chequeDetails),
				HttpStatusCode.valueOf(200));

	}

	@Override
	public ResponseEntity<?> claimCheque(ChequeDetails chequeDetails) {
		return new ResponseEntity<List<ChequeDetails>>(chequeDetailsRepository.claimCheque(chequeDetails),
				HttpStatusCode.valueOf(200));
	}

	@Override
	public ResponseEntity<?> updateCheque1(ChequeDetails chequeDetails) {
	if(chequeDetails.getChequeStatus().equals("Not recieved")) {
      		return new ResponseEntity<Boolean>(chequeDetailsRepository.updateCheque1(chequeDetails),
				HttpStatusCode.valueOf(200));
	}else {
		return new ResponseEntity<Boolean>(false, HttpStatusCode.valueOf(400));
	}
	}

	@Override
	public ResponseEntity<?> hasCheque(int accountDetails) {
		return new ResponseEntity<Boolean>(chequeDetailsRepository.haveCheque(accountDetails), HttpStatusCode.valueOf(200));
	}
}
