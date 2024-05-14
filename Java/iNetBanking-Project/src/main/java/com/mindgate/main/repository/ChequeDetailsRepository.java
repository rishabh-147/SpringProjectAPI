package com.mindgate.main.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.AccountDetails;
import com.mindgate.main.domain.ChequeDetails;

@Repository
public class ChequeDetailsRepository implements ChequeDetailsRepositoryInterface {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final String INSERT_INTO_CHECK = "INSERT INTO Cheque_details (issuer_account_no, cheque_no, benificiary_account_no, deposit_date, cheque_amount, cheque_status)values(?,?,?,?,?,?)";
	private static final String GET_CHEQUE_DETAILS = "SELECT * FROM cheque_details WHERE cheque_no = ?";

	@Override
	public boolean AddChequeDetails(ChequeDetails chequeDetails) {
		Object[] parameters = { chequeDetails.getIssuerAccountNumber(), chequeDetails.getChequeNumber(),
				chequeDetails.getBenificiaryAccountNumber(), chequeDetails.getDepositDate(),
				chequeDetails.getChequeAmount(), chequeDetails.getChequeStatus() };

		int result = jdbcTemplate.update(INSERT_INTO_CHECK, parameters);
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public ChequeDetails getChequeDetails(String cheque_no) {
	    ChequeDetails chequeDetails = jdbcTemplate.queryForObject(GET_CHEQUE_DETAILS,
	        (rs, r) -> {
	            AccountDetails accountDetails1 = new AccountDetails();
	            AccountDetails accountDetails2 = new AccountDetails();

	            accountDetails1.setAccountNumber(rs.getInt("issuer_account_no"));
	            accountDetails2.setAccountNumber(rs.getInt("benificiary_account_no"));

	            return new ChequeDetails(
	                accountDetails1,
	                rs.getString("cheque_no"),
	                accountDetails2,
	                rs.getDate("deposit_date"),
	                rs.getDouble("cheque_amount"),
	                rs.getString("cheque_status")
	            );
	        },
	        cheque_no
	    );
	    
	    return chequeDetails; // jdbcTemplate.queryForObject handles null check internally
	}

}











