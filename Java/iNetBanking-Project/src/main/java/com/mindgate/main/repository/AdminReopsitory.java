package com.mindgate.main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.AccountDetails;
import com.mindgate.main.domain.ChequeDetails;
import com.mindgate.main.domain.UserDetails;

@Repository
public class AdminReopsitory implements AdminRepositoryInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String GET_ALL_INACTIVE_USERS = "SELECT * FROM user_details WHERE login_active = 'false' AND login_count = 0";
	private static final String GET_ALL_CHEQUE_UNCLEARED = "SELECT a.*, b.ACCOUNT_NUMBER AS issuer_account_number, b.USER_ID AS issuer_user_id, b.ACCOUNT_TYPE AS issuer_account_type, b.ACTUAL_BALANCE AS issuer_actual_balance, b.OVERDERAFT_OPTED AS issuer_overdraft_opted, b.OVERDRAFT_BALANCE AS issuer_overdraft_balance, b.OVERDRAFT_CHARGES AS issuer_overdraft_charges, c.ACCOUNT_NUMBER AS bf_account_number, c.USER_ID AS bf_user_id, c.ACCOUNT_TYPE AS bf_account_type, c.ACTUAL_BALANCE AS bf_actual_balance, c.OVERDERAFT_OPTED AS bf_overdraft_opted, c.OVERDRAFT_BALANCE AS bf_overdraft_balance, c.OVERDRAFT_CHARGES AS bf_overdraft_charges, u1.email_id AS issuer_email, u2.email_id AS beneficiary_email FROM ACCOUNT_DETAILS b JOIN cheque_details a ON a.issuer_account_no = b.ACCOUNT_NUMBER JOIN ACCOUNT_DETAILS c ON a.benificiary_account_no = c.ACCOUNT_NUMBER JOIN USER_DETAILS u1 ON b.user_id = u1.user_id JOIN USER_DETAILS u2 ON c.user_id = u2.user_id WHERE a.cheque_status IN('sent for clearence')";
//	private static final String GET_ALL_SEIZED_ACCOUNT = "SELECT * FROM account_details a JOIN user_details u ON a.user_id = u.user_id WHERE account_status = 'false'";
	private static final String ACTIVATE_USER_LOGIN = "UPDATE user_details SET login_active = 'true', login_count= 0 WHERE user_id =?";
	private static final String VERIFY_CHEQUE_DETAILS = "UPDATE cheque_details SET cheque_status = ?";
	private static final String GET_ALL_LOCKED_USERS = "SELECT * FROM user_details WHERE login_active = 'false' AND login_count = 3";
	private static final String UPDATE_CHEQUE_STATUS = "UPDATE cheque_details SET cheque_status = ? WHERE cheque_no = ?";

	@Override
	public List<UserDetails> getAllInactiveUsers() {
		List<UserDetails> details = jdbcTemplate.query(GET_ALL_INACTIVE_USERS,
				(rs, r) -> new UserDetails(rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("password"), rs.getDate("dob"), rs.getString("user_type"),
						rs.getString("email_id"), rs.getString("gender"), rs.getString("address"),
						rs.getLong("phone_number"), rs.getDate("reg_date"), rs.getInt("login_count"),
						rs.getString("login_active")));

		if (details != null)
			return details;
		return null;

	}

	@Override
	public List<ChequeDetails> getAllUnClearedCheques() {
		ChequeDetailsRowMapper chequeDetailsRowMapper = new ChequeDetailsRowMapper();
		List<ChequeDetails> details = jdbcTemplate.query(GET_ALL_CHEQUE_UNCLEARED, chequeDetailsRowMapper);

		if (details != null)
			return details;
		return null;
	}

	/*
	 * @Override
	 *
	 * public List<AccountDetails> getAllSeizedAccount() {
	 * 
	 * List<AccountDetails> seizedAccount =
	 * jdbcTemplate.query(GET_ALL_SEIZED_ACCOUNT, new AccountDetailsRowMapper()); if
	 * (seizedAccount != null) return seizedAccount; return null; }
	 */
	
	@Override
	public boolean activateUser(int userId) {

		int res = jdbcTemplate.update(ACTIVATE_USER_LOGIN, userId);

		if (res > 0)
			return true;
		return false;
	}

	@Override
	public boolean updateChequeStatus(ChequeDetails chequeDetails) {
		String chequeStatus = chequeDetails.getChequeStatus(); String chequeNumber= chequeDetails.getChequeNumber();
		Object[] args = { chequeStatus, chequeNumber };
		int res = jdbcTemplate.update(UPDATE_CHEQUE_STATUS, args);

		if (res > 0)
			return true;
		return false;
	}

	@Override
	public List<UserDetails> getAllLockedUsers() {
		List<UserDetails> details = jdbcTemplate.query(GET_ALL_LOCKED_USERS,
				(rs, r) -> new UserDetails(rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("password"), rs.getDate("dob"), rs.getString("user_type"),
						rs.getString("email_id"), rs.getString("gender"), rs.getString("address"),
						rs.getLong("phone_number"), rs.getDate("reg_date"), rs.getInt("login_count"),
						rs.getString("login_active")));

		if (details != null)
			return details;
		return null;
	}

}
