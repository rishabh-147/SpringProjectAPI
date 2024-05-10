package com.mindgate.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.ChequeDetails;
import com.mindgate.main.repository.ChequeDetailsRepositoryInterface;
@Service
public class ChequeDetailsService implements ChequeDetailsServiceInterface {
	@Autowired
	private ChequeDetailsRepositoryInterface chequeDetailsRepositoryInterface;
	@Override
	public boolean AddChequeDetails(ChequeDetails chequeDetails) {
		
		return chequeDetailsRepositoryInterface.AddChequeDetails(chequeDetails);
	}

}
