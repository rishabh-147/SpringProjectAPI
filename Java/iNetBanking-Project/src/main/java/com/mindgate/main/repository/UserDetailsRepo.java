package com.mindgate.main.repository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.UserDetails;
import com.mindgate.main.exception.UserDetailsDoesNotExist;

@Repository
public class UserDetailsRepo implements UserDetailsRepoInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// STRINGS SQL
	private static final String INSERT_USER = "INSERT INTO user_details (first_name, last_name, password, dob, user_type, Email_Id, Gender, address, phone_number, Reg_Date, login_count, login_active) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_USER_BY_ID = "SELECT * FROM user_details WHERE email_id = ?";
	private static final String GET_USER_BY_USERID = "SELECT * FROM user_details WHERE user_id = ?";
	private static final String UPDATE_LOGIN_COUNT = "UPDATE user_details SET login_count = ?, login_active = ? WHERE user_id = ?";
	private static final String UPDATE_USER_DETAILS = "UPDATE user_details SET  EMAIL_ID = ? ,PHONE_NUMBER=?,first_name=?,last_name=?,address=? where USER_ID=?";
	@Override
	public UserDetails addUser(UserDetails userDetails) {
		Object[] args = { userDetails.getFirstName(), userDetails.getLastName(), userDetails.getPassword(),
				userDetails.getDateOfBirth(), userDetails.getUserType(), userDetails.getEmailId(),
				userDetails.getGender(), userDetails.getAddress(), userDetails.getPhoneNumber(),
				LocalDateTime.now(), userDetails.getLoginCount(), userDetails.getLoginActive() };

		int result = jdbcTemplate.update(INSERT_USER, args);

		if (result == 1)
			return userDetails;
		else
			return null;
	}

	@Override
	public UserDetails getById(String emailId) {

		UserDetails userDetails = null;
		// System.out.println("################################################" +
		// jdbcTemplate);

		try {
			userDetails = jdbcTemplate.queryForObject(GET_USER_BY_ID,
					(rs, r) -> new UserDetails(rs.getInt("user_id"), rs.getString("first_name"),
							rs.getString("last_name"), rs.getString("password"), rs.getDate("dob"),
							rs.getString("user_type"), rs.getString("email_id"), rs.getString("gender"),
							rs.getString("address"), rs.getLong("phone_number"), rs.getDate("reg_date"),
							rs.getInt("login_count"), rs.getString("login_active")),
					emailId);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new UserDetailsDoesNotExist();
		}

		if (userDetails != null)
			return userDetails;
		return null;

	}

	public UserDetails getByUserId(int userId) {
		UserDetails userDetails = jdbcTemplate.queryForObject(GET_USER_BY_USERID,
				(rs, r) -> new UserDetails(userId, rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("password"), rs.getDate("dob"), rs.getString("user_type"),
						rs.getString("email_id"), rs.getString("gender"), rs.getString("address"),
						rs.getLong("phone_number"), rs.getDate("reg_date"), rs.getInt("login_count"),
						rs.getString("login_active")),
				userId);

		if (userDetails != null)
			return userDetails;
		return null;

	}

	public UserDetails userExists(String emailId) {

		UserDetails userDetails = null;
		// System.out.println("################################################" +
		// jdbcTemplate);

		try {
			userDetails = jdbcTemplate.queryForObject(GET_USER_BY_ID,
					(rs, r) -> new UserDetails(rs.getInt("user_id"), rs.getString("first_name"),
							rs.getString("last_name"), rs.getString("password"), rs.getDate("dob"),
							rs.getString("user_type"), rs.getString("email_id"), rs.getString("gender"),
							rs.getString("address"), rs.getLong("phone_number"), rs.getDate("reg_date"),
							rs.getInt("login_count"), rs.getString("login_active")),
					emailId);

		} catch (Exception e) {
			System.out.println("UserRepo.userExists--> " + e.getMessage());
			return null;
		}

		if (userDetails != null)
			return userDetails;
		return null;

	}

	public boolean updateLogin(int user_id, int loginCount, String loginActive) {
		

		if (loginCount >= 3)
			loginActive = "false";
		else
			loginActive = "true";

		Object[] args = { loginCount, loginActive, user_id };
		
		int res = jdbcTemplate.update(UPDATE_LOGIN_COUNT, args);
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$"+res);
		System.out.println("------------------" + args[1]);
		System.out.println("------------------" + args[2]);
		System.out.println("------------------" + args[0]);
		if (res > 0)
			return true;
		return false;
	}
	@Override
    public boolean existDetail(UserDetails userdetails) {
        Object [] parameters= {userdetails.getEmailId(),userdetails.getPhoneNumber(),userdetails.getFirstName(),userdetails.getLastName(),userdetails.getAddress(),userdetails.getUserId()};
        int result=jdbcTemplate.update(UPDATE_USER_DETAILS,parameters);
        if(result>0) {
            return true;
        }
        return false;
    }
}
