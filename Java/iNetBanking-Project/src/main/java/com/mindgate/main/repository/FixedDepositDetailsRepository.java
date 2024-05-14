package com.mindgate.main.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.AccountDetails;
import com.mindgate.main.domain.FixedDepositDetails;
import com.mindgate.main.domain.UserDetails;

@Repository
public class FixedDepositDetailsRepository implements FixedDepositDetailsRepositoryInterface {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final String INSERT_INTO_FIXED = "INSERT INTO fixed_deposit_details (user_id, fd_creation_date, fd_amount, fd_tenure, fd_maturity_date, rate_of_interest, account_number) VALUES (?, ? , ? , ? , ?, ? , ?)";

	private static final String GET_FD = "SELECT * FROM fixed_deposit_details WHERE user_id = ?";
	@Override
	public FixedDepositDetails addFixedDeposit(FixedDepositDetails fixedDepositDetails) {

	
		 Object[] parameters = { fixedDepositDetails.getUserDetails().getUserId() , fixedDepositDetails.getFixedDepositCreationDate(),
				fixedDepositDetails.getFixedDepositAmount(), fixedDepositDetails.getFixedDepositTenure(), fixedDepositDetails.getFixedDepositMaturityDate(),
				fixedDepositDetails.getRateOfInterest(), fixedDepositDetails.getAccountDetails().getAccountNumber() };
		 
		
		 
		int result = jdbcTemplate.update(INSERT_INTO_FIXED, parameters);
		
		if (result > 0) {
			return fixedDepositDetails;
		}
		return null;
	}

	@Override
	public List<FixedDepositDetails> getFD(int userId) {
		
		List<FixedDepositDetails> deposits  = jdbcTemplate.query(GET_FD, new FixedDepositDetailsRowMapper(), userId);
		
		if(deposits != null)
			return deposits;
		
		return null;
	}

}
