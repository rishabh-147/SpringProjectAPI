package com.mindgate.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.main.domain.AccountDetails;
import com.mindgate.main.domain.FixedDepositDetails;
import com.mindgate.main.service.FixedDepositDetailsServiceInterface;
import com.mindgate.main.service.UserDetailsServiceInterface;

@RestController
@RequestMapping("fd-details")
public class FixedDepositDetailsController {
	
	@Autowired
	private FixedDepositDetailsServiceInterface fixedDepositDetailsServiceInterface;
	@PostMapping("create-fd")
	public boolean AddFixedDetail(@RequestBody FixedDepositDetails fixedDepositDetails) {
		boolean result = fixedDepositDetailsServiceInterface.addFixedDeposit(fixedDepositDetails);
		return result;
	}

}
