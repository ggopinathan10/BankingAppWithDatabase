package com.business.ExceptionHandler;

import java.sql.SQLException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class BankingAppGlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(UserAuthorizationException.class)
	public ResponseEntity<ResponseInfo> handleUserLoginException(UserAuthorizationException userAuthorizationException){
		System.out.println("inside exception handler logic");
		ResponseInfo responseInfo = new ResponseInfo(userAuthorizationException.getMessage(), "Authentication Failed");
		return new ResponseEntity<ResponseInfo>(responseInfo, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(BankingException.class)
	public ResponseEntity<ResponseInfo> handleUserLoginException(BankingException bankingException){
		System.out.println("inside exception handler logic");
		ResponseInfo responseInfo = new ResponseInfo(bankingException.getMessage(), "Banking error");
		return new ResponseEntity<ResponseInfo>(responseInfo, HttpStatus.BAD_REQUEST);
	}

}
