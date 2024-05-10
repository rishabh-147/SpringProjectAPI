package com.mindgate.main.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.AccountDetails;
import com.mindgate.main.domain.FixedDepositDetails;

@Repository
public class FixedDepositDetailsRepository implements FixedDepositDetailsRepositoryInterface {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final String INSERT_INTO_FIXED = "insert into FIXED_DEPOSIT_DETAILS(user_id,fd_creation_date,fd_amount,fd_tenure,fd_maturity_date,rate_of_interest,account_number)values(?,SYSDATE,?,?,SYSDATE,?,?)";

	@Override
	public boolean addFixedDeposit(FixedDepositDetails fixedDepositDetais) {
		System.out.println("fixed" + fixedDepositDetais);
		Object[] parameters = { fixedDepositDetais.getUserDetails().getUserId(),
				fixedDepositDetais.getFixedDepositAmount(), fixedDepositDetais.getFixedDepositTenure(),
				fixedDepositDetais.getRateOfInterest(), fixedDepositDetais.getAccountDetails().getAccountNumber() };
		int result = jdbcTemplate.update(INSERT_INTO_FIXED, parameters);
		if (result > 0) {
			return true;
		}
		return false;
	}

}
