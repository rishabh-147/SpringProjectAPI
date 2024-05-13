package com.mindgate.main.repository;

import com.mindgate.main.domain.UserDetails;

public interface UserDetailsRepoInterface {

	public UserDetails getById(String emailId);
	public UserDetails getByUserId(int userId);
	public UserDetails addUser(UserDetails userDetails);
	public UserDetails userExists(String emailId);	
	public boolean updateLogin(int user_id, int loginCount, String loginActive);
//	public boolean updateLoginActive(int user_id, String loginActive);
	
}

