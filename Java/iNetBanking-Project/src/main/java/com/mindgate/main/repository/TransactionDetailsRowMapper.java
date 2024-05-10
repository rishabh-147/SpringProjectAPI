package com.mindgate.main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mindgate.main.domain.TransactionDetails;

public class TransactionDetailsRowMapper  implements RowMapper<TransactionDetails>{

	@Override
	public TransactionDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		TransactionDetails transaction = new TransactionDetails();
	        transaction.setTransactionId(rs.getInt("transactionId"));
	        transaction.setIssuerAccountNumber(rs.getLong("issuerAccountNumber"));
	        transaction.setBenificiaryAccountNumber(rs.getLong("benificiaryAccountNumber"));
	        transaction.setTransactionType(rs.getString("transactionType"));
	        transaction.setTransactionMode(rs.getString("transactionMode"));
	        transaction.setTransactionAmount(rs.getDouble("transactionAmount"));
	        transaction.setRemarks(rs.getString("remarks"));
	        transaction.setTransactionStatus(rs.getString("transactionStatus"));
	        transaction.setTransactionDate(rs.getString("transactionDate"));
	        return transaction;
	}

}
