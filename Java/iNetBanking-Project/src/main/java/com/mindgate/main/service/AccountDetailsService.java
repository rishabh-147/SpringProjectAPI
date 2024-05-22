package com.mindgate.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.AccountDetails;
import com.mindgate.main.repository.AccountDetailsRepositoryInterface;

@Service
public class AccountDetailsService implements AccountDetailsServiceInterface {
	@Autowired
	private AccountDetailsRepositoryInterface accountDetailsRepository;
	

	@Override
	public ResponseEntity<List<AccountDetails>> getById(int userId) {
		return new ResponseEntity<List<AccountDetails>>( accountDetailsRepository.getById(userId), HttpStatusCode.valueOf(200));

	}

	@Override
	public ResponseEntity<AccountDetails> getByAccount(long accounNumber) {

		return new ResponseEntity<AccountDetails>(accountDetailsRepository.getByAccount(accounNumber),
				HttpStatusCode.valueOf(200));
	}

	@Override
	public ResponseEntity<?> addAccount(AccountDetails accountDetails) {

		return new ResponseEntity<AccountDetails> (accountDetailsRepository.addAccount(accountDetails), HttpStatusCode.valueOf(200));
	}
}
