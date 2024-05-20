package com.mindgate.main.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.AccountDetails;
import com.mindgate.main.domain.ChequeDetails;

@Repository
public class ChequeDetailsRepository implements ChequeDetailsRepositoryInterface {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final String INSERT_INTO_CHEQUE = "INSERT INTO Cheque_details (issuer_account_no)values(?)";
	private static final String GET_CHEQUE_DETAILS = "SELECT * FROM cheque_details WHERE cheque_no = ?";
	private static final String GET_BY_ACCOUNT="SELECT u.*, acc_issuer.* FROM Cheque_details u INNER JOIN account_details acc_issuer ON u.issuer_account_no = acc_issuer.account_number WHERE u.issuer_account_no = ?";
	private static final String UPDATE_CHEQUE = "UPDATE cheque-details SET benificiary_account_no=?,deposit_date=?,cheque_amount=?,cheque_status=? WHERE cheque_no=?";

	@Override
	public boolean AddChequeDetails(ChequeDetails chequeDetails) {
				
		int result = jdbcTemplate.update(INSERT_INTO_CHEQUE, chequeDetails.getIssuerAccountNumber().getAccountNumber());
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateCheque(ChequeDetails chequeDetails) {
		Object[] args = {chequeDetails.getBenificiaryAccountNumber().getAccountNumber(), chequeDetails.getDepositDate(), chequeDetails.getChequeAmount(), chequeDetails.getChequeStatus(), chequeDetails.getChequeNumber()};
		int res = jdbcTemplate.update(UPDATE_CHEQUE, args);
		if(res > 0)
			return true;
		return false;
	}
	
	@Override
	public ChequeDetails getChequeDetails(String cheque_no) {
	    ChequeDetails chequeDetails = jdbcTemplate.queryForObject(GET_CHEQUE_DETAILS,
	        (rs, r) -> {
	            AccountDetails accountDetails1 = new AccountDetails();
	            AccountDetails accountDetails2 = new AccountDetails();

	            accountDetails1.setAccountNumber(rs.getInt("issuer_account_no"));
	            

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
	@Override
    public List<ChequeDetails> getByAccountNumber(int accountNumber) {
        // TODO Auto-generated method stub
        List<ChequeDetails> chequeDetails =  jdbcTemplate.query(GET_BY_ACCOUNT,
                (rs, r) -> {
                    AccountDetails accountDetails1 = new AccountDetails();
                    AccountDetails accountDetails2 = new AccountDetails();

                    accountDetails1.setAccountNumber(rs.getInt("issuer_account_no"));
                    accountDetails1.setAccountType(rs.getString("account_type"));
                    accountDetails1.setCharges(rs.getDouble("overdraft_charges"));
                    accountDetails1.setOverdraftBalance(rs.getDouble("overdraft_balance"));
                    accountDetails1.setOvredraftedOpted(rs.getString("overderaft_opted"));
                    accountDetails1.setActualBalance(rs.getDouble("actual_balance"));
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
                accountNumber
        );
        return chequeDetails;
    }
}











