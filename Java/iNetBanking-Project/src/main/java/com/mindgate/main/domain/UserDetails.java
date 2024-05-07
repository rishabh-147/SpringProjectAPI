package com.mindgate.main.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class UserDetails {

	private int  userId;
	private String firstName;
	private String lastName;
	private String password;
	private Date dateOfBirth;
	private String userType;
	private String emailId;
	private String gender;
	private String address;
	private long phoneNumber;
	private Date registrationDate;
	private int loginCount;
	private String loginActive;
	
	
	
}
