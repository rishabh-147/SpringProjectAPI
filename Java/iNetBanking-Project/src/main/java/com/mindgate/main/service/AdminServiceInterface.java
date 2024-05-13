package com.mindgate.main.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mindgate.main.domain.AccountDetails;
import com.mindgate.main.domain.ChequeDetails;
import com.mindgate.main.domain.UserDetails;

public interface AdminServiceInterface {
	public ResponseEntity<List<UserDetails>> getAllInactiveUsers();

	public ResponseEntity<List<UserDetails>> getAllLockedUsers();
	
	ResponseEntity<List<ChequeDetails>> getAllUnclearedCheques();
	
	public ResponseEntity<?> activateUser(int userId);
	//public ResponseEntity<List<AccountDetails>> getAllSeizedAccount();
}
