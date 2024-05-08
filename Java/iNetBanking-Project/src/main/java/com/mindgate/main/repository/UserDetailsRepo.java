package com.mindgate.main.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.UserDetails;
import com.mindgate.main.exception.UserDetailsDoesNotExist;

@Repository
public class UserDetailsRepo implements UserDetailsRepoInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// STRINGS SQL

	private static final String GET_USER_BY_ID = "SELECT * FROM user_details WHERE email_id = ?";

	@Override
	public UserDetails getById(String emailId) {

		UserDetails userDetails = null;
		try {
		userDetails = jdbcTemplate.queryForObject(GET_USER_BY_ID,
				(rs, r) -> new UserDetails(rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("password"), rs.getDate("dob"), rs.getString("user_type"),
						rs.getString("email_id"), rs.getString("gender"), rs.getString("address"),
						rs.getLong("phone_number"), rs.getDate("reg_date"), rs.getInt("login_count"),
						rs.getString("login_active")),
				emailId);
		
		}
		catch(Exception e) {
			throw new UserDetailsDoesNotExist("Email not found");
		}
		if (userDetails != null)
			return userDetails;
		return null;
	}

	
}
