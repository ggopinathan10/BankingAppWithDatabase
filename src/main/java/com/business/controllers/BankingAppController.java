package com.business.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;

import com.business.BankEntity.AccountDetailsEntity;
import com.business.BankEntity.BankDetailsEntity;
import com.business.BankEntity.BankNamesEntity;
import com.business.BankEntity.BankUsersEntity;
import com.business.BankEntity.TransactionDetailsEntity;
import com.business.ExceptionHandler.BankingException;
import com.business.ExceptionHandler.ResponseInfo;
import com.business.ExceptionHandler.UserAuthorizationException;
import com.business.Service.BankingService;

@RestController
@RequestMapping("/api")
public class BankingAppController {
	
	@Autowired BankingService bankingService;
	private List<String> banksList;
	private List<BankDetailsEntity> bankDetailsEntityList;
	private String loggedinUsername;
	
		
//	@PostMapping(value = "/register")
//	public ResponseEntity<ResponseInfo> registerNewUser(@RequestBody BankUsersEntity user){
//		return bankingService.registerNewUser(user);
//	}
//	
//	@PostMapping(value = "/login")
//	public String login(@RequestParam("username") String username, @RequestParam("password") String password) throws UserAuthorizationException {
//		loggedinUsername = "";
//		if(bankingService.checkUserCredentials(username, password)) {
//			loggedinUsername = username;
//			return "You are now logged in as " +loggedinUsername;
//		}else {
//			throw new UserAuthorizationException("Invalid login credentials, Please check and try again");
//		}
//	}


//	@GetMapping(value = "/getBankData/{bankName}")
//	public BankDetailsEntity getBankData(@PathVariable String bankName) {
//		return bankingService.getBankData(bankName);
//	}
	
	@GetMapping(value = "/getBankList")
	public List<BankNamesEntity> getBankList() {
		return bankingService.getAllBankNames();
	}
	
	@GetMapping(value = "/addNewUser")
	public void addNewUser() {
		bankingService.addNewUser();
	}
	
	@PostMapping(value = "/logintoBank/{bankName}/{username}/{password}/{corpId}")
	public String loginToBank(@PathVariable String bankName, @PathVariable String username, @PathVariable String password,
			@PathVariable int corpId) throws UserAuthorizationException {
		boolean isSuccessful = bankingService.loginToBank(bankName, username, password, corpId);
		if(isSuccessful) {
			loggedinUsername = username;
			return "You are now logged in as "+username;
		}else {
			throw new UserAuthorizationException("You are not authorized to log into "+bankName+" Bank");
		}
	}
	
	@GetMapping(value = "/getAccounts/{bankName}")
	public List<AccountDetailsEntity> getAccounts(@PathVariable String bankName) throws BankingException, UserAuthorizationException {
		if(loggedinUsername != null && !loggedinUsername.isEmpty()) {
			return bankingService.getAccountDetails(bankName, loggedinUsername);
		}else {
			throw new UserAuthorizationException("You have not logged into "+bankName+" Bank");
		}
	}
	
	@GetMapping(value = "/getTransactionData/{accountNumber}")
	public List<TransactionDetailsEntity> getTransactionData(@PathVariable int accountNumber) throws BankingException {
		return bankingService.getAllTransactions(accountNumber);
	}
	
	
	

	

}
