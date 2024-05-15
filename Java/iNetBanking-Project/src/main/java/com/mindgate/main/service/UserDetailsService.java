
package com.mindgate.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.UserDetails;
import com.mindgate.main.exception.LoginLimitException;
import com.mindgate.main.exception.PasswordMismatchException;
import com.mindgate.main.exception.UserAlreadyExists;
import com.mindgate.main.exception.UserDetailsDoesNotExist;
import com.mindgate.main.exception.UserNotAddedException;
import com.mindgate.main.repository.UserDetailsRepoInterface;

@Service
public class UserDetailsService implements UserDetailsServiceInterface {

	@Autowired
	private UserDetailsRepoInterface userDetailsRepo;
	@Autowired
	private AccountDetailsServiceInterface accountDetailsService;

	@Override
	public ResponseEntity<?> addUser(UserDetails userDetails) {
		if (userDetailsRepo.userExists(userDetails.getEmailId()) != null) {
			// already a user exception

			throw new UserAlreadyExists("User already exists");
		}
		UserDetails details = userDetailsRepo.addUser(userDetails);
		if (details != null)
			return new ResponseEntity<UserDetails>(details, HttpStatusCode.valueOf(200));
		else
			throw new UserNotAddedException("Internal Error");
	}

	@Override
	public ResponseEntity<?> getById(String emailId) {
		if (userDetailsRepo.getById(emailId) != null)
			return new ResponseEntity<UserDetails>(userDetailsRepo.getById(emailId), HttpStatusCode.valueOf(200));
		else
			throw new UserDetailsDoesNotExist();
	}

	@Override
	public ResponseEntity<?> verifylogin(UserDetails userDetails) {
		String email = userDetails.getEmailId();
		String password = userDetails.getPassword();
		boolean res = false;

		UserDetails details = userDetailsRepo.getById(email); // throws UserNotFoundException if user not found in repo
//Login activbe if false deny login if true proceed
		if (details.getUserType().equals("user") && details.getLoginActive().equals("true")) { //added true condition

			if (details.getPassword().equals(password) && details.getLoginCount() < 3 ) {
				return new ResponseEntity<UserDetails>(details, HttpStatusCode.valueOf(200));
			} else {
				// check the current login count
				if (details.getLoginCount() < 3) {
					// update the loginCount by 1
					userDetailsRepo.updateLogin(details.getUserId(), details.getLoginCount() + 1, "true");
					throw new PasswordMismatchException("password do not match");
				} else {
					userDetailsRepo.updateLogin(userDetails.getUserId(), userDetails.getLoginCount() + 1, "true");
					throw new LoginLimitException("you exceeded the login Limit");
					// login >= 3 Max attempts reached
				}
			}
		} else {
			throw new UserDetailsDoesNotExist();
		}
	}

	@Override
	public ResponseEntity<?> verifyadminlogin(UserDetails userDetails) {
		UserDetails details = userDetailsRepo.getById(userDetails.getEmailId());

		// System.out.println("!!!!!!!!!!!!!!!!@@@@@@@@@@@@@@@@@@@" + details);
		if (details.getUserType().equals("admin")) {

			if (details.getPassword().equals(userDetails.getPassword()) && details.getLoginCount() < 3) {
				return new ResponseEntity<UserDetails>(details, HttpStatusCode.valueOf(200));
			} else {
				// check the current login count
				if (details.getLoginCount() < 3) {
					// update the loginCount by 1

					throw new PasswordMismatchException("Admin password Mismatch!!!");
				} else {

					throw new LoginLimitException("Maximum admin login limit reached , contact Developer Team");
					// login >= 3 Max attempts reached
				}
			}
		} else {
			throw new UserDetailsDoesNotExist("User details does not exists");
		}
	}

	@Override
	public ResponseEntity<?> getByUserId(int userId) {
		if (userDetailsRepo.getByUserId(userId) != null)
			return new ResponseEntity<UserDetails>(userDetailsRepo.getByUserId(userId), HttpStatusCode.valueOf(200));
		else
			throw new UserDetailsDoesNotExist();
	}

}
