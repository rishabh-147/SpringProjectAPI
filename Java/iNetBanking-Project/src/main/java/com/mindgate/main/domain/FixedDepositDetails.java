package com.mindgate.main.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FixedDepositDetails {
	private int fixedDepositId;
	private UserDetails userDetails;
	private  String fixedDepositCreationDate ;
	private  double fixedDepositAmount;
	private double fixedDepositTenure;
	private String fixedDepositMaturityDate;
	private double rateOfInterest;
	private  AccountDetails accountDetails;

	
}
