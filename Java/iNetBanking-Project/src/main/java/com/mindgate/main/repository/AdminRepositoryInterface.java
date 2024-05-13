package com.mindgate.main.repository;

import java.util.List;

import com.mindgate.main.domain.AccountDetails;
import com.mindgate.main.domain.ChequeDetails;
import com.mindgate.main.domain.UserDetails;

public interface AdminRepositoryInterface {
	//get All creation request -- give users having account_active as false to make true
	public List<UserDetails> getAllInactiveUsers();
	
	//get all the users who have been locked out of their accout due to max limit of password
	public List<UserDetails> getAllLockedUsers();
	// update for account validation 
	public boolean activateUser(int userId);
	
	
	//get all cheque validation	-- give cheque having status as NOT RECEIVED, SENT FOR CLEARANCE;
	public List<ChequeDetails> getAllUnClearedCheques();

	// update for cheque
		//1. bounced and clear
	public boolean updateChequeStatus();
	
	
	
	// account seizure
	//public List<AccountDetails> getAllSeizedAccount();
	
	
	

}
