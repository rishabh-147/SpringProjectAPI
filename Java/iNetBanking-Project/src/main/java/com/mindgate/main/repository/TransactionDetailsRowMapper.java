package com.mindgate.main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mindgate.main.domain.TransactionDetails;

public class TransactionDetailsRowMapper implements RowMapper<TransactionDetails> {

	@Override
	public TransactionDetails mapRow(ResultSet rs, int rowNum) throws SQLException {

		TransactionDetails transaction = new TransactionDetails();

		transaction.setTransactionId(rs.getInt("transaction_Id"));
		transaction.setIssuerAccountNumber(rs.getLong("issuer_Account_No"));
		transaction.setBenificiaryAccountNumber(rs.getLong("benificiary_Account_No"));
		transaction.setTransactionType(rs.getString("transaction_Type"));
		transaction.setTransactionMode(rs.getString("transaction_Mode"));
		transaction.setTransactionAmount(rs.getDouble("transaction_Amount"));
		transaction.setRemarks(rs.getString("remarks"));
		transaction.setTransactionStatus(rs.getString("transaction_Status"));
		transaction.setTransactionDate(rs.getString("transaction_Date"));

		return transaction;
	}

}
