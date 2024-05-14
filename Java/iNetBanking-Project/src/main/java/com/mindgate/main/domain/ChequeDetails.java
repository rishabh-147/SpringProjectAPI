package com.mindgate.main.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChequeDetails {
	private AccountDetails issuerAccountNumber;
	private String chequeNumber;
	private AccountDetails benificiaryAccountNumber;
	private Date depositDate;
	private double chequeAmount;
	private String chequeStatus;
}
