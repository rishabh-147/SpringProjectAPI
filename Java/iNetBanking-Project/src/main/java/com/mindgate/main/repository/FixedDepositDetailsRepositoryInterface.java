package com.mindgate.main.repository;

import java.util.List;

import com.mindgate.main.domain.FixedDepositDetails;

public interface FixedDepositDetailsRepositoryInterface {
	public FixedDepositDetails addFixedDeposit(FixedDepositDetails fixedDepositDetais);
	public List<FixedDepositDetails> getFD(int userId);
}
