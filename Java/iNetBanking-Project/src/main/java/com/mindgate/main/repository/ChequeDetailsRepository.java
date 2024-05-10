package com.mindgate.main.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.ChequeDetails;
@Repository
public class ChequeDetailsRepository implements ChequeDetailsRepositoryInterface {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final String INSERT_INTO_CHECK="INSERT INTO Cheque_details (issuer_account_no, cheque_no, benificiary_account_no, deposit_date, cheque_amount, cheque_status)values(?,?,?,?,?,?)";
			
	@Override
	public boolean AddChequeDetails(ChequeDetails chequeDetails) {
		Object[] parameters= {chequeDetails.getIssuerAccountNumber(),chequeDetails.getCheque_number(),chequeDetails.getBenificiaryAccountNumber(),chequeDetails.getDepositDate(),chequeDetails.getChequeAmount(),chequeDetails.getChequeStatus()};

		int result=jdbcTemplate.update(INSERT_INTO_CHECK, parameters);
		if(result>0) {
			return true;
		}
		return false;
	}

}
