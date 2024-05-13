package com.mindgate.main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.mindgate.main.domain.AccountDetails;
import com.mindgate.main.domain.ChequeDetails;
import com.mindgate.main.domain.UserDetails;

public class ChequeDetailsRowMapper implements RowMapper<ChequeDetails>{

	@Override
	public ChequeDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		//Issuer Account Details
		
		UserDetails issuerDetails = new UserDetails();
		
		AccountDetails issuerAccount  = new AccountDetails();
		issuerAccount.setAccountNumber(rs.getInt("issuer_account_number"));
		//issuerAccount.setAccountStatus(rs.getString("issuer_account_status"));
		issuerAccount.setAccountType(rs.getString("issuer_account_type"));

		issuerAccount.setUserDetails(issuerDetails);
		//issuerDetails = detailsRepo.getByUserId();
		issuerDetails.setUserId(rs.getInt("issuer_user_id"));
		
		issuerAccount.setActualBalance(rs.getDouble("issuer_actual_balance"));
		issuerAccount.setCharges(rs.getDouble("issuer_overdraft_charges"));
		issuerAccount.setOverdraftBalance(rs.getDouble("issuer_overdraft_balance"));
		issuerAccount.setOvredraftedOpted(rs.getString("issuer_overdraft_opted"));
		//Beneficiary Account Details
		UserDetails beneficiaryDetails = new UserDetails();
		AccountDetails beneficiaryAccount  = new AccountDetails();
		beneficiaryAccount.setAccountNumber(rs.getInt("bf_account_number"));
		System.out.println("**************************************************");
		//beneficiaryAccount.setAccountStatus(rs.getString("bf_account_status"));
		System.out.println("+++++++++++++++++++++++++++++++++++");		
		beneficiaryAccount.setAccountType(rs.getString("bf_account_type"));

		beneficiaryAccount.setUserDetails(beneficiaryDetails);
		beneficiaryDetails.setUserId(rs.getInt("bf_user_id"));
		
		beneficiaryAccount.setActualBalance(rs.getDouble("bf_actual_balance"));
		beneficiaryAccount.setCharges(rs.getDouble("bf_overdraft_charges"));
		beneficiaryAccount.setOverdraftBalance(rs.getDouble("bf_overdraft_balance"));
		beneficiaryAccount.setOvredraftedOpted(rs.getString("bf_overdraft_opted"));
		
		//Cheque Details object
		ChequeDetails chequeDetails = new ChequeDetails();
		chequeDetails.setBenificiaryAccountNumber(beneficiaryAccount);
		chequeDetails.setCheque_number(rs.getInt("cheque_no"));
		chequeDetails.setChequeAmount(rs.getDouble("cheque_amount"));
		chequeDetails.setDepositDate(rs.getDate("deposit_date"));
		chequeDetails.setChequeStatus(rs.getString("cheque_status"));
		chequeDetails.setIssuerAccountNumber(issuerAccount);
		
		
		return chequeDetails;
	}

}
