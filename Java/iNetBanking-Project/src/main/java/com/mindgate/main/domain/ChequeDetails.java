package com.mindgate.main.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChequeDetails {
	private int issuerAccountNumber;
	private String cheque_number;
	private int benificiaryAccountNumber;
	private String depositDate;
	private double chequeAmount;
	private String chequeStatus;
}
