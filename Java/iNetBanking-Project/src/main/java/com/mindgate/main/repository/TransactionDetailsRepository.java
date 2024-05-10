package com.mindgate.main.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.TransactionDetails;
@Repository
public class TransactionDetailsRepository  implements TransactionDetailsRepositoryInterface{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final String INSERT_INTO_TRANSACTION="INSERT INTO transaction_details (issuer_account_no, benificiary_account_no, transaction_type, transaction_mode, transaction_amount, remarks, transaction_status, transaction_date)values(?,?,?,?,?,?,?,?)";
		
	@Override
	public boolean addTransactionDetails(TransactionDetails transactionDetails) {
		Object[] parameters= {transactionDetails.getIssuerAccountNumber(),
				transactionDetails.getBenificiaryAccountNumber(),transactionDetails.getTransactionType(),transactionDetails.getTransactionMode()
				,transactionDetails.getTransactionAmount(),transactionDetails.getRemarks(),transactionDetails.getTransactionStatus()
				,transactionDetails.getTransactionDate()};
		int result=jdbcTemplate.update(INSERT_INTO_TRANSACTION, parameters);
		if(result>0) {
			return true;
		}
		return false;
	}

}
