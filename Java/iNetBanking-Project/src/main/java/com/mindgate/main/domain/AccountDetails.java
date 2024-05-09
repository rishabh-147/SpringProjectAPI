package com.mindgate.main.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDetails {
	private int accountNumber;
	private UserDetails userDetails;
	private String accountType;
	private String accountStatus;
	private double actualBalance;
	private String OvredraftedOpted;
	private double overdraftBalance;
	private double charges;
	
}
