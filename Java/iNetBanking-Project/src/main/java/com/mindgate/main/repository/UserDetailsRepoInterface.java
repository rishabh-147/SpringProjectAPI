package com.mindgate.main.repository;

import com.mindgate.main.domain.UserDetails;

public interface UserDetailsRepoInterface {

	public UserDetails getById(String emailId);
	public UserDetails addUser(UserDetails userDetails);
}

