
package com.mindgate.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.UserDetails;
import com.mindgate.main.exception.UserDetailsDoesNotExist;
import com.mindgate.main.repository.UserDetailsRepoInterface;

@Service
public class UserDetailsService implements UserDetailsServiceInterface{
	
	@Autowired
	private UserDetailsRepoInterface userDetailsRepo;

	@Override
	public ResponseEntity<?> getById(String emailId) {
		if(userDetailsRepo.getById(emailId) != null )
			return new ResponseEntity<UserDetails>(userDetailsRepo.getById(emailId), HttpStatusCode.valueOf(200));
		else
			throw new UserDetailsDoesNotExist();
	}

	@Override
	public ResponseEntity<?> verifylogin(UserDetails userDetails) {
		int loginCount = 0;
		String loginActive = "false";
		if(userDetailsRepo.getById(userDetails.getEmailId()) != null) {
			
		
		loginCount = userDetailsRepo.getById(userDetails.getEmailId()).getLoginCount();
		loginActive = userDetailsRepo.getById(userDetails.getEmailId()).getLoginActive();
		}
		if(loginActive.equals("true") && loginCount <= 3) {
			return new ResponseEntity<Boolean>(userDetailsRepo.verifylogin(userDetails), HttpStatusCode.valueOf(200));
		}
		System.out.println("login failed due to account validation/maximum tries reached.");
		throw new UserDetailsDoesNotExist();
	}

}
