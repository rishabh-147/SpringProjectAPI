package com.mindgate.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.main.domain.AccountDetails;
import com.mindgate.main.domain.ChequeDetails;
import com.mindgate.main.service.ChequeDetailsServiceInterface;

@RestController
@RequestMapping("chequedetails")
public class ChequeDetailsController {
	@Autowired
	private ChequeDetailsServiceInterface chequeDetailsServiceInterface;
	@RequestMapping(value="update",method=RequestMethod.POST)
	public boolean AddChequeDetail(@RequestBody ChequeDetails chequeDetails) {
		boolean result=chequeDetailsServiceInterface.AddChequeDetails(chequeDetails);
		return result;
	}

}
