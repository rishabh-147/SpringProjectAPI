package com.mindgate.main.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mindgate.main.domain.FixedDepositDetails;

public interface FixedDepositDetailsServiceInterface {
	
	public ResponseEntity<?> addFixedDeposit(FixedDepositDetails fixedDepositDetais);
	public ResponseEntity<List<FixedDepositDetails>> getFd(int userId);
}
