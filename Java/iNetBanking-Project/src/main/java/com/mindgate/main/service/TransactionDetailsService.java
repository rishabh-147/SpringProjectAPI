package com.mindgate.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.TransactionDetails;
import com.mindgate.main.repository.TransactionDetailsRepositoryInterface;
@Service
public class TransactionDetailsService  implements TransactionDetailsServiceInterface{
	@Autowired
	private TransactionDetailsRepositoryInterface transactionDetailsRepo;
	@Override
	public boolean addTransactionDetails(TransactionDetails transactionDetails) {
		// TODO Auto-generated method stub
		return transactionDetailsRepo.addTransactionDetails(transactionDetails);
	}
	@Override
	public ResponseEntity<List<TransactionDetails>> getbyIssuerAccId(long accNumber) {
		
		return new ResponseEntity<List<TransactionDetails>> (transactionDetailsRepo.getbyIssuerAccId(accNumber), HttpStatusCode.valueOf(200));
	}

}
