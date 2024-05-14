package com.mindgate.main.domain;

import java.time.LocalDateTime;
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
	private  Date fixedDepositCreationDate ;
	private  double fixedDepositAmount;
	private double fixedDepositTenure;
	private Date fixedDepositMaturityDate;
	private double rateOfInterest;
	private  AccountDetails accountDetails;

	
}
