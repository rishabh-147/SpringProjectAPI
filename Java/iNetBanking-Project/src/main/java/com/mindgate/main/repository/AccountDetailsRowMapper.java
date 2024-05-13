package com.mindgate.main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mindgate.main.domain.AccountDetails;
import com.mindgate.main.domain.UserDetails;

public class AccountDetailsRowMapper implements RowMapper<AccountDetails>{

	@Override
	public AccountDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserDetails userDetails = new UserDetails(rs.getInt("user_id"), rs.getString("first_name"),
				rs.getString("last_name"), rs.getString("password"), rs.getDate("dob"),
				rs.getString("user_type"), rs.getString("email_id"), rs.getString("gender"),
				rs.getString("address"), rs.getLong("phone_number"), rs.getDate("reg_date"),
				rs.getInt("login_count"), rs.getString("login_active"));

		AccountDetails accountDetails = new AccountDetails(rs.getInt("account_number"), userDetails,
				rs.getString("account_type"), 
				rs.getDouble("actual_balance"), rs.getString("overderaft_opted"),
				rs.getDouble("overdraft_balance"), rs.getDouble("overdraft_charges"));

		return accountDetails;
	}

}
