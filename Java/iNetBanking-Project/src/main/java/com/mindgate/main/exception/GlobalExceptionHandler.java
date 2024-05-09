package com.mindgate.main.exception;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotAddedException.class)
	public ResponseEntity<ProblemDetail> userNotAddedException(UserNotAddedException userNotAddedException) {
		ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		problemDetail.setTitle("User details not added");
		problemDetail.setType(URI.create("http://localhost:8080/user-details/add-user-details"));
		problemDetail.setProperty("host", "localhost");
		problemDetail.setProperty("port", "8080");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problemDetail);
	}
	
	@ExceptionHandler(UserDetailsDoesNotExist.class)
	public ResponseEntity<ProblemDetail> userDetailsDoesNotExist(UserDetailsDoesNotExist userDetailsDoesNotExist) {
		ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
		problemDetail.setTitle("User details does not exist in the dB");
		problemDetail.setType(URI.create("http://localhost:8080/userdetails/getbyId"));
		problemDetail.setProperty("host", "localhost");
		problemDetail.setProperty("port", "8080");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);

	}
	
	@ExceptionHandler(PasswordMismatchException.class)
	public ResponseEntity<ProblemDetail> passwordMismatch(PasswordMismatchException passwordMismatchException) {
		ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
		problemDetail.setTitle("Password Mismatch!!");
		problemDetail.setType(URI.create("http://localhost:8080/userdetails/verifylogin"));
		problemDetail.setProperty("host", "localhost");
		problemDetail.setProperty("port", "8080");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);

	}
	
	@ExceptionHandler(LoginLimitException.class)
	public ResponseEntity<ProblemDetail> loginLimitException(LoginLimitException loginLimitException) {
		ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
		problemDetail.setTitle("Maximum limits reached!! Contact Administrator!");
		problemDetail.setType(URI.create("http://localhost:8080/userdetails/verifylogin"));
		problemDetail.setProperty("host", "localhost");
		problemDetail.setProperty("port", "8080");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);

	}
	
	@ExceptionHandler(UserAlreadyExists.class)
	public ResponseEntity<ProblemDetail> userAlreadyExists(UserAlreadyExists userAlreadyExists) {
		ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
		problemDetail.setTitle("User already exists, kindly login!");
		problemDetail.setType(URI.create("http://localhost:8080/userdetails/adduser"));
		problemDetail.setProperty("host", "localhost");
		problemDetail.setProperty("port", "8080");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);

	}
}
