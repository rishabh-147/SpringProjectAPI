package com.mindgate.main.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mindgate.main.domain.AccountDetails;

public interface AccountDetailsServiceInterface {
	public List<AccountDetails>  getById(int userId);
	public AccountDetails addAccount(AccountDetails accountDetails);



}
