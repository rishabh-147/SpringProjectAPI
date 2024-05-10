package com.mindgate.main.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetails {
	private int transactionId;
	private long issuerAccountNumber;
	private long benificiaryAccountNumber;
	private String transactionType;
	private String transactionMode;
	private  double transactionAmount;
	private String remarks;
	private String transactionStatus;
	private String transactionDate;
	
	
}
