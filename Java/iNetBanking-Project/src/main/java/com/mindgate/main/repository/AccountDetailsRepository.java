package com.mindgate.main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.AccountDetails;
import com.mindgate.main.domain.UserDetails;
import com.mindgate.main.exception.UserDetailsDoesNotExist;

@Repository
public class AccountDetailsRepository implements AccountDetailsRepositoryInterface {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String GET_ALL_ACCOUNT = "select u.*,acc.* from user_details u inner join account_details acc on u.user_id = acc.user_id where acc.user_id=?";
	private static final String INSERT_ACCOUNT = "INSERT INTO account_details (user_id, account_type, actual_balance, overderaft_opted, overdraft_balance, overdraft_charges) VALUES(?,?,?,?,?,?)";

	private static final double OVERDRAFT_BALANCE = 50000.00;

	@Override
	public AccountDetails addAccount(AccountDetails accountDetails) {
		Object[] args = { accountDetails.getUserDetails(), accountDetails.getAccountType(),
				accountDetails.getActualBalance(), accountDetails.getOvredraftedOpted(), OVERDRAFT_BALANCE,
				accountDetails.getCharges() };

		int res = jdbcTemplate.update(INSERT_ACCOUNT, args);
		if (res > 0)
			return accountDetails;
		else
			return null;
	}

	@Override
	public List<AccountDetails> getById(int userId) {
		try {
			return jdbcTemplate.query(GET_ALL_ACCOUNT, new RowMapper<AccountDetails>() {

				@Override
				public AccountDetails mapRow(ResultSet rs, int rowNum) throws SQLException {

					UserDetails userDetails = new UserDetails(userId, rs.getString("first_name"),
							rs.getString("last_name"), rs.getString("password"), rs.getDate("dob"),
							rs.getString("user_type"), rs.getString("email_id"), rs.getString("gender"),
							rs.getString("address"), rs.getLong("phone_number"), rs.getDate("reg_date"),
							rs.getInt("login_count"), rs.getString("login_active"));

					AccountDetails accountDetails = new AccountDetails(rs.getInt("account_number"), userDetails,
							rs.getString("account_type"), rs.getString("account_status"),
							rs.getDouble("actual_balance"), rs.getString("overderaft_opted"),
							rs.getDouble("overdraft_balance"), rs.getDouble("overdraft_charges"));

					return accountDetails;
				}

			}, userId);
		} catch (Exception e) {
			// throw exception
			throw new UserDetailsDoesNotExist();
		}

	}

}
