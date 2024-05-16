package com.mindgate.main.repository;

import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.AccountDetails;
import com.mindgate.main.domain.TransactionDetails;

@Repository
public class TransactionDetailsRepository implements TransactionDetailsRepositoryInterface {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String INSERT_INTO_TRANSACTION = "INSERT INTO transaction_details (issuer_account_no, benificiary_account_no, transaction_type, transaction_mode, transaction_amount, remarks, transaction_status, transaction_date)values(?,?,?,?,?,?,?,?)";
	private static final String GET_TRANSACTIONS_ISSUER = "SELECT * FROM transaction_details WHERE issuer_account_no = ?";
	
	@Override
	public boolean addTransactionDetails(TransactionDetails transactionDetails) {
	//	System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&" + transactionDetails);

		Object[] parameters = { transactionDetails.getIssuerAccountDetails().getAccountNumber(),
				transactionDetails.getBenificiaryAccountDetails().getAccountNumber(),
				transactionDetails.getTransactionType(), transactionDetails.getTransactionMode(),
				transactionDetails.getTransactionAmount(), transactionDetails.getRemarks(),
				transactionDetails.getTransactionStatus(), LocalDateTime.now() };
		int result = jdbcTemplate.update(INSERT_INTO_TRANSACTION, parameters);
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<TransactionDetails> getbyIssuerAccId(long accNumber) {
		RowMapper<TransactionDetails> mapper = new TransactionDetailsRowMapper();
		List<TransactionDetails> details = jdbcTemplate.query(GET_TRANSACTIONS_ISSUER, mapper, accNumber);

		if (details != null)
			return details;
		else
			// NoDataException
			return null;

	}

	
}
