package com.mindgate.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.FixedDepositDetails;
import com.mindgate.main.repository.FixedDepositDetailsRepositoryInterface;

@Service
public class FixedDepositDetailsService implements FixedDepositDetailsServiceInterface {
	@Autowired
	private FixedDepositDetailsRepositoryInterface fixedDepositDetailsRepositoryInterface;
	@Override
	public boolean addFixedDeposit(FixedDepositDetails fixedDepositDetais) {

		return fixedDepositDetailsRepositoryInterface.addFixedDeposit(fixedDepositDetais);
	}

}
