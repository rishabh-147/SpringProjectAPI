package com.mindgate.main.repository;

import java.util.List;

import com.mindgate.main.domain.ChequeDetails;

public interface ChequeDetailsRepositoryInterface {
	public boolean AddChequeDetails(ChequeDetails chequeDetails);
	public boolean updateCheque(ChequeDetails chequeDetails);
	public ChequeDetails getChequeDetails(String cheque_no);
	public List<ChequeDetails> getByAccountNumber(int accountNumber);
	public boolean updateCheque1(ChequeDetails chequeDetails);
	boolean hasCheque(int accountDetails);
	List<ChequeDetails> claimCheque(ChequeDetails chequeDetails);
}
