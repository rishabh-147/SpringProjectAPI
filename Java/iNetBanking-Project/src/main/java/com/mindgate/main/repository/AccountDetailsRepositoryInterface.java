package com.mindgate.main.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mindgate.main.domain.AccountDetails;

public interface AccountDetailsRepositoryInterface {
	public  List<AccountDetails> getById(int userId);
	public AccountDetails addAccount(AccountDetails accountDetails);
	//Update account balance --debit credit, and also based on account type
	public AccountDetails updateAccount(AccountDetails accountDetails);
}
