package com.mindgate.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.AccountDetails;
import com.mindgate.main.repository.AccountDetailsRepositoryInterface;
@Service
public class AccountDetailsService  implements AccountDetailsServiceInterface{
	@Autowired
	private AccountDetailsRepositoryInterface accountDetailsRepository;
	@Override
	public List<AccountDetails> getById(int userId){
		return accountDetailsRepository.getById(userId);
		
	}
	
	@Override
	public ResponseEntity<AccountDetails> getByAccount(long accounNumber) {
		
		return new ResponseEntity<AccountDetails>(accountDetailsRepository.getByAccount(accounNumber), HttpStatusCode.valueOf(200));
	}
	
	@Override
	public AccountDetails addAccount(AccountDetails accountDetails) {
		return accountDetailsRepository.addAccount(accountDetails);
	}

	/*
	 * @Override public AccountDetails creditAccount(AccountDetails accountDetails,
	 * double amount) { double actualBalance = accountDetails.getActualBalance();
	 * double overdraftBal = accountDetails.getOverdraftBalance(); double
	 * overdraftCharges = accountDetails.getCharges(); String accountType =
	 * accountDetails.getAccountType();
	 * 
	 * accountDetails.setActualBalance(actualBalance);
	 * accountDetails.setCharges(overdraftCharges);
	 * accountDetails.setOverdraftBalance(overdraftBal); return
	 * accountDetailsRepository.creditAccount(accountDetails); }
	 */
	
	

}
