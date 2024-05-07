package com.mindgate.main.repository;

import com.mindgate.main.domain.UserDetails;

public interface UserDetailsServiceInterface {
	public UserDetails getById(String emailId);
}
