package com.mindgate.main.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class ChequeDetails {
	@NonNull
	private AccountDetails issuerAccountNumber;
	private String chequeNumber;
	private AccountDetails benificiaryAccountNumber;
	private Date depositDate;
	private double chequeAmount;
	private String chequeStatus;
	
	public ChequeDetails() {
		// TODO Auto-generated constructor stub
	}

	
	
	public ChequeDetails(@NonNull AccountDetails issuerAccountNumber, String chequeNumber,
			AccountDetails benificiaryAccountNumber, Date depositDate, double chequeAmount, String chequeStatus) {
		super();
		this.issuerAccountNumber = issuerAccountNumber;
		this.chequeNumber = chequeNumber;
		this.benificiaryAccountNumber = benificiaryAccountNumber;
		this.depositDate = depositDate;
		this.chequeAmount = chequeAmount;
		this.chequeStatus = chequeStatus;
	}



	public AccountDetails getIssuerAccountNumber() {
		return issuerAccountNumber;
	}

	public void setIssuerAccountNumber(AccountDetails issuerAccountNumber) {
		this.issuerAccountNumber = issuerAccountNumber;
	}

	public String getChequeNumber() {
		return chequeNumber;
	}

	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	public AccountDetails getBenificiaryAccountNumber() {
		return benificiaryAccountNumber;
	}

	public void setBenificiaryAccountNumber(AccountDetails benificiaryAccountNumber) {
		this.benificiaryAccountNumber = benificiaryAccountNumber;
	}

	public Date getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}

	public double getChequeAmount() {
		return chequeAmount;
	}

	public void setChequeAmount(double chequeAmount) {
		this.chequeAmount = chequeAmount;
	}

	public String getChequeStatus() {
		return chequeStatus;
	}

	public void setChequeStatus(String chequeStatus) {
		this.chequeStatus = chequeStatus;
	}

	@Override
	public String toString() {
		return "ChequeDetails [issuerAccountNumber=" + issuerAccountNumber + ", chequeNumber=" + chequeNumber
				+ ", benificiaryAccountNumber=" + benificiaryAccountNumber + ", depositDate=" + depositDate
				+ ", chequeAmount=" + chequeAmount + ", chequeStatus=" + chequeStatus + "]";
	}
	
	
	
	
}
