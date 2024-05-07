package com.mindgate.main.repository;

import java.util.List;

import com.mindgate.main.domain.UserDetails;

public interface UserDetailsRepoInterface {

	public UserDetails getById(String emailId);
	public List<UserDetails> getAll();
	
}
