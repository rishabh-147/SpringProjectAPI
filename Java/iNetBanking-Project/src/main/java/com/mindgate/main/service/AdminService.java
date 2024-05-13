package com.mindgate.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.AccountDetails;
import com.mindgate.main.domain.ChequeDetails;
import com.mindgate.main.domain.UserDetails;
import com.mindgate.main.repository.AdminRepositoryInterface;

@Service
public class AdminService implements AdminServiceInterface {

	@Autowired
	private AdminRepositoryInterface adminRepository;

	@Override
	public ResponseEntity<List<UserDetails>> getAllInactiveUsers() {

		return new ResponseEntity<List<UserDetails>>(adminRepository.getAllInactiveUsers(),
				HttpStatusCode.valueOf(200));
	}
	
	@Override
	public ResponseEntity<List<UserDetails>> getAllLockedUsers() {
		
		return new ResponseEntity<List<UserDetails>>(adminRepository.getAllLockedUsers(),
				HttpStatusCode.valueOf(200));
	}

	@Override
	public ResponseEntity<List<ChequeDetails>> getAllUnclearedCheques() {
		return new ResponseEntity<List<ChequeDetails>>(adminRepository.getAllUnClearedCheques(),
				HttpStatusCode.valueOf(200));
	}

	@Override
	public ResponseEntity<Boolean> activateUser(int userId) {
		return new ResponseEntity<Boolean>(adminRepository.activateUser(userId), HttpStatusCode.valueOf(200));
	}



	/*
	 * @Override public ResponseEntity<List<AccountDetails>> getAllSeizedAccount() {
	 * 
	 * return new
	 * ResponseEntity<List<AccountDetails>>(adminRepository.getAllSeizedAccount(),
	 * HttpStatusCode.valueOf(200)); }
	 */
}
