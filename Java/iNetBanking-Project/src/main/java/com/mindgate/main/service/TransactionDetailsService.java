package com.mindgate.main.service;

import java.time.chrono.MinguoChronology;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.AccountDetails;
import com.mindgate.main.domain.TransactionDetails;
import com.mindgate.main.repository.AccountDetailsRepositoryInterface;
import com.mindgate.main.repository.TransactionDetailsRepository;
import com.mindgate.main.repository.TransactionDetailsRepositoryInterface;

@Service
public class TransactionDetailsService implements TransactionDetailsServiceInterface {
	@Autowired
	private TransactionDetailsRepositoryInterface transactionDetailsRepo;
	@Autowired
	private AccountDetailsRepositoryInterface accountDetailsRepository;
	private static final double MIN_BALANCE = 5000.00;
	private static final double OVERDRAFT_BALANCE = 50000.00;
	private static final double OVERDRAFT_CHARGE = 0.10; // 10%
	private static final double BOUNCED_CHARGE = 100.00;
	@Override	
	public ResponseEntity<?> addTransactionDetails(TransactionDetails transactionDetails) {
		// TODO Auto-generated method stub
		return new ResponseEntity<Boolean> (transactionDetailsRepo.addTransactionDetails(transactionDetails),HttpStatusCode.valueOf(200));
	}

	@Override
	public ResponseEntity<List<TransactionDetails>> getbyIssuerAccId(long accNumber) {

		return new ResponseEntity<List<TransactionDetails>>(transactionDetailsRepo.getbyIssuerAccId(accNumber),
				HttpStatusCode.valueOf(200));
	}

	private double calcODCharge(double overdraftUsed) {
		return overdraftUsed * OVERDRAFT_CHARGE; // 10% of overdraftUsed
	}

	@Override
	public ResponseEntity<Boolean> performTransaction(TransactionDetails details) {
		int transFlag = 0;
		// System.out.println("======================= IN
		// pERFOM========================");
		AccountDetails issuer = accountDetailsRepository
				.getByAccount(details.getIssuerAccountDetails().getAccountNumber());
		AccountDetails beneficiary = accountDetailsRepository
				.getByAccount(details.getBenificiaryAccountDetails().getAccountNumber());
		double transAmount = details.getTransactionAmount();
		double issuerBal = issuer.getActualBalance();
		double issuerODBal = issuer.getOverdraftBalance();
		double issuerCharge = issuer.getCharges();

		double beneficiaryBal = beneficiary.getActualBalance();
		double beneficiaryODBal = beneficiary.getOverdraftBalance();
		double beneficiaryCharge = beneficiary.getCharges();
		
		if (issuer.getAccountType().equalsIgnoreCase("saving")) {
			if (beneficiary.getAccountType().equalsIgnoreCase("saving")) {
				// debit from user
				if ((issuerBal - MIN_BALANCE) >= transAmount) {
					issuerBal -= transAmount;
					beneficiaryBal += transAmount;
					transFlag = 1;
				} else {
					// throw insufficient balance exception
					System.out.println("======================= INSUFFICIENT BALANCE ========================");

				}
			} else {// if beneficiatry hahs a CURRENT account
				if ((issuerBal - MIN_BALANCE) >= transAmount) {
					if (beneficiaryCharge != 0.0) { // if there is some ODcharge
						transAmount -= beneficiaryCharge;
						beneficiaryCharge = 0;
					}
					if (beneficiaryODBal < OVERDRAFT_BALANCE) { // if there is OD used, it will be filled first
						if (transAmount >= (OVERDRAFT_BALANCE - beneficiaryODBal)) {
							transAmount -= OVERDRAFT_BALANCE - beneficiaryODBal;
							beneficiaryODBal = OVERDRAFT_BALANCE;
						} else {
							beneficiaryODBal += transAmount; // If trans amount is less than the od Balance of issuer,
																// it soukld just fill up the OD BALANCE
						}
						if (transAmount > 0) { // if still some thing is left in transAmpount add it to beneficiary bal
							beneficiaryBal += transAmount;
						}
					} else { // if no OD used, directly add to beneficiary Bal
						beneficiaryBal += transAmount;
					}
					transFlag = 1;
				} else {
					// throw insufficient balance exception
					System.out.println("======================= INSUFFICIENT BALANCE ========================");
				}
			}
		}

		else { // if issuer has a CURRENT account
			if (beneficiary.getAccountType().equalsIgnoreCase("saving")) { // if bene has saving account
				if (issuerBal >= transAmount) { // IF issuer HAS ENOUGH IN ACCOUNT BALAANCE
					issuerBal -= transAmount;
					beneficiaryBal += transAmount;
					transAmount = 0.0;
					transFlag = 1;
				} else { // IF NOT, THEN DEBIT FROM OD and BAL
					double tAmt = transAmount;
					if ((issuerODBal + issuerBal) >= transAmount) {

						transAmount -= issuerBal;
						issuerBal = 0;
						issuerCharge = calcODCharge(transAmount);
						issuerODBal -= transAmount;
						beneficiaryBal += tAmt;
						transFlag = 1;
					} else {
						// throw insufficient exception
						System.out.println("======================= INSUFFICIENT BALANCE ========================");
						transFlag = 0;
					}
				}
			} else { // if bene has cuurent account
				if (issuerBal >= transAmount) { // if issuer has enough balance
					System.out.println("BENE IS CURRENT");

					if (beneficiaryCharge != 0.0) { // if there is some ODcharge
						transAmount -= beneficiaryCharge;
						beneficiaryCharge = 0;
					}
					if (beneficiaryODBal < OVERDRAFT_BALANCE) { // if there is OD used, it will be filled first
						if (transAmount >= OVERDRAFT_BALANCE - beneficiaryODBal) {
							transAmount -= OVERDRAFT_BALANCE - beneficiaryODBal;
							beneficiaryODBal = OVERDRAFT_BALANCE;
						}
						if (transAmount > 0) { // if still some thing is left in transAmpount add it to beneficiary bal
							beneficiaryBal += transAmount;
						}
					} else { // if no OD used, directly add to beneficiary Bal
						beneficiaryBal += transAmount;
					}
				} else { // if user doesn't have enough money
					if (issuerODBal > transAmount) {

						double tAmt = transAmount;
						// Complete debit process from issuer
						transAmount -= issuerBal;
						issuerBal = 0;
						issuerODBal -= transAmount;
						issuerCharge = calcODCharge(transAmount);

						// now credit part into user

						if (beneficiaryCharge != 0.0) { // if there is some ODcharge
							tAmt -= beneficiaryCharge;
							beneficiaryCharge = 0;
						}
						if (beneficiaryODBal < OVERDRAFT_BALANCE) { // if there is OD used, it will be filled first
							if (tAmt >= (OVERDRAFT_BALANCE - beneficiaryODBal)) {
								tAmt -= OVERDRAFT_BALANCE - beneficiaryODBal;
								// System.out.println("-----------------------" + OVERDRAFT_BALANCE);
								beneficiaryODBal = OVERDRAFT_BALANCE;
							}
							if (tAmt > 0) { // if still some thing is left in transAmpount add it to beneficiary
											// bal
								beneficiaryBal += tAmt;
							}
						} else { // if no OD used, directly add to beneficiary Bal
							beneficiaryBal += tAmt;
						}
						transFlag = 1;
					} else {
						// throw insufficient exception
						transFlag = 0;
					}

				}
			}
		}

		issuer.setActualBalance(issuerBal);
		issuer.setOverdraftBalance(issuerODBal);
		issuer.setCharges(issuerCharge);

		beneficiary.setActualBalance(beneficiaryBal);
		beneficiary.setOverdraftBalance(beneficiaryODBal);
		beneficiary.setCharges(beneficiaryCharge);

		if (transFlag == 1) {

			accountDetailsRepository.updateAccount(issuer);
			accountDetailsRepository.updateAccount(beneficiary);
			details.setTransactionStatus("success");
			//debit record update in transactions
			updateTransaction(details);
			
			//swapping accounts for credit entry
			long issuerAccountNumber = issuer.getAccountNumber();
			long beneAccountNumber = beneficiary.getAccountNumber();
			
			details.getBenificiaryAccountDetails().setAccountNumber((int) issuerAccountNumber);
			details.getIssuerAccountDetails().setAccountNumber((int) beneAccountNumber);
			details.setTransactionType("credit");
			updateTransaction(details);
			return new ResponseEntity<Boolean>(true, HttpStatusCode.valueOf(200));
		} else {
			details.setTransactionType("credit");
			details.setTransactionStatus("failed");
			transactionDetailsRepo.addTransactionDetails(details);
			return new ResponseEntity<Boolean>(updateTransaction(details), HttpStatusCode.valueOf(500));
		}

	}
	
	private boolean updateTransaction(TransactionDetails details) {
		return transactionDetailsRepo.addTransactionDetails(details);
	}
	
	public ResponseEntity<Boolean> bouncedCheque(TransactionDetails details){
		AccountDetails issuer = accountDetailsRepository
				.getByAccount(details.getIssuerAccountDetails().getAccountNumber());
		AccountDetails beneficiary = accountDetailsRepository
				.getByAccount(details.getBenificiaryAccountDetails().getAccountNumber());
		
		double issuerBal = issuer.getActualBalance();
		double beneficiaryBal = beneficiary.getActualBalance();
		if(issuerBal > BOUNCED_CHARGE && beneficiaryBal > BOUNCED_CHARGE) {
			issuerBal -= BOUNCED_CHARGE;
			beneficiaryBal -= BOUNCED_CHARGE;
		}
		else {
			issuerBal = 0;
			beneficiaryBal = 0;
		}
		
		issuer.setActualBalance(issuerBal);
		beneficiary.setActualBalance(beneficiaryBal);
		
		details.setBenificiaryAccountDetails(beneficiary);
		details.setIssuerAccountDetails(issuer);
		
		details.setTransactionAmount(BOUNCED_CHARGE);
		details.setRemarks("BOUNCED CHEQUE");
		
		return new ResponseEntity<Boolean>(transactionDetailsRepo.addTransactionDetails(details)
				, HttpStatusCode.valueOf(200));
	}

}
