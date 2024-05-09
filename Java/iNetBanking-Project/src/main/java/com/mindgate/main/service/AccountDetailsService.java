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
	private AccountDetailsRepositoryInterface accountDetailsRepositoryInterface;
	@Override
	public List<AccountDetails> getById(int userId){
		return accountDetailsRepositoryInterface.getById(userId);
		
	}
	

}
