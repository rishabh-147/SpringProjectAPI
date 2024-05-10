package com.mindgate.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.TransactionDetails;
import com.mindgate.main.repository.TransactionDetailsRepositoryInterface;
@Service
public class TransactionDetailsService  implements TransactionDetailsServiceInterface{
	@Autowired
	private TransactionDetailsRepositoryInterface transactionDetailsRepositoryInterface;
	@Override
	public boolean addTransactionDetails(TransactionDetails transactionDetails) {
		// TODO Auto-generated method stub
		return transactionDetailsRepositoryInterface.addTransactionDetails(transactionDetails);
	}

}
