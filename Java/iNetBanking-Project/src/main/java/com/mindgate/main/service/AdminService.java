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

		return new ResponseEntity<List<UserDetails>>(adminRepository.getAllLockedUsers(), HttpStatusCode.valueOf(200));
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

	@Override
	public ResponseEntity<?> updateChequeStatus(ChequeDetails chequeDetails) {

		return new ResponseEntity<Boolean>(adminRepository.updateChequeStatus(chequeDetails),
				HttpStatusCode.valueOf(200));
	}

	@Override
	public ResponseEntity<?> verifyChequeClearance(ChequeDetails chequeDetails) {
		int f = 0;
		double issuerAccountBalance = chequeDetails.getIssuerAccountNumber().getActualBalance();
		double chequeAmount = chequeDetails.getChequeAmount();
		if (chequeDetails.getIssuerAccountNumber().getAccountType().equalsIgnoreCase("saving")) {
			if (issuerAccountBalance >= chequeAmount)
				f = 1;
		} else {
			// In case of current account, overdraft also comes into picture
			issuerAccountBalance += chequeDetails.getIssuerAccountNumber().getOverdraftBalance();
			if (issuerAccountBalance >= chequeAmount)
				f = 1;
		}
		if (f == 1)
			return new ResponseEntity<Boolean>(true, HttpStatusCode.valueOf(200));
		return new ResponseEntity<Boolean>(false, HttpStatusCode.valueOf(200));

	}

	/*
	 * @Override public ResponseEntity<List<AccountDetails>> getAllSeizedAccount() {
	 * 
	 * return new
	 * ResponseEntity<List<AccountDetails>>(adminRepository.getAllSeizedAccount(),
	 * HttpStatusCode.valueOf(200)); }
	 */
}
