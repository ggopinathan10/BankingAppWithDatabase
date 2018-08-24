package com.business.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.business.BankEntity.AccountDetailsEntity;
import com.business.BankEntity.BankDetailsEntity;
import com.business.BankEntity.BankNamesEntity;
import com.business.BankEntity.BankUsersEntity;
import com.business.BankEntity.TransactionDetailsEntity;
import com.business.ExceptionHandler.BankingException;
import com.business.ExceptionHandler.ResponseInfo;
import com.business.ExceptionHandler.UserAuthorizationException;
import com.business.repository.BankAccountRepository;
import com.business.repository.BankDAO;
import com.business.repository.BankMetaDAO;
import com.business.repository.BankTransactionRepository;
import com.business.repository.UserDAO;

@Service
public class BankingService {
	
	@Autowired @Qualifier("userdao") UserDAO userDao;
	@Autowired @Qualifier("bankdao") BankDAO bankDao;
	@Autowired @Qualifier("bankMetaDAO") BankMetaDAO bankMetaDao;
	@Autowired @Qualifier("txndao") BankTransactionRepository txnDao;
	@Autowired @Qualifier("accountdao") BankAccountRepository accountDao;
	
//	public ResponseEntity<ResponseInfo> registerNewUser(BankUsersEntity user) {
//		ResponseInfo responseInfo = null;
//		try {
//			
//			userDao.save(user);
//			responseInfo = new ResponseInfo("Your account has been created successfully", "Registration successful");
//			return new ResponseEntity<ResponseInfo>(responseInfo, HttpStatus.OK);
//		}catch(DataIntegrityViolationException dte) {
//			responseInfo = new ResponseInfo("This user already exists, Please try with a different username", "Validation failure");
//			return new ResponseEntity<ResponseInfo>(responseInfo, HttpStatus.BAD_REQUEST);
//		}
//	}
//
//
//	public boolean checkUserCredentials(String username, String password) throws UserAuthorizationException {
//		boolean isValid = false;
//		if(username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
//			BankUsersEntity currentUser = userDao.findByUsername(username);
//			if(currentUser != null) {
//				if(currentUser.getPassword().equals(password)) {
//					isValid = true;
//				}
//			}
//		}
//		return isValid;
//		
//	}
	
	public List<BankNamesEntity> getAllBankNames() {
		return bankDao.findAll();
	}


//	public BankDetailsEntity getBankData(String bankName) {
//		return bankMetaDao.findByBankName(bankName);
//	}
	
	public void addNewUser() {
		
		/**add bank details*/
		BankDetailsEntity bankDetails = new BankDetailsEntity(0, "HDFC");
		bankMetaDao.save(bankDetails);
		
		/**add users to the bank*/
		BankDetailsEntity bankDetails1 = bankMetaDao.findByBankName("HDFC");
		BankUsersEntity bankUsers = new BankUsersEntity(0, "sunil", "welcome", bankDetails1);
		userDao.save(bankUsers);
		
		/**add account for users*/
		BankUsersEntity bankUsers1 = userDao.findByUsername("sunil");
		AccountDetailsEntity accountDetails = new AccountDetailsEntity(0, 432, "Savings", bankUsers1);
		accountDao.save(accountDetails);
		
		/**add transactions*/
		AccountDetailsEntity accountDetails1 = accountDao.findByAccountNumber(432);
		TransactionDetailsEntity txnDE = new TransactionDetailsEntity(0, new Date(), 1343.4, "Credit", accountDetails1);
		txnDao.save(txnDE);
		
		
	}


	public boolean loginToBank(String bankName, String username, String password, int corpId) {
		boolean isSuccessful = false;
		if(username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
			List<BankUsersEntity> usersList = userDao.findAllByUsername(username);
			Optional<BankUsersEntity> matchingObject = usersList.stream().filter(bue -> bue.getBankDetails().getBankName().equals(bankName)).findFirst();
			if(matchingObject.isPresent()) {
				BankUsersEntity bankUser = matchingObject.get();
				if(bankUser != null && bankUser.getPassword().equals(password)) {
					BankDetailsEntity bde = bankUser.getBankDetails();
					if(bde != null && bde.getBankName().equals(bankName)) {
						isSuccessful = true;
					}
				}
			}			
		}
		return isSuccessful;
	}


	public List<AccountDetailsEntity> getAccountDetails(String bankName, String loggedinUsername) throws BankingException {
		List<AccountDetailsEntity> adeList = accountDao.findAll();
		List<AccountDetailsEntity> userAccountDetails = adeList.stream().filter(ade -> ade.getBankUsers().getUsername().equals(loggedinUsername) && 
				ade.getBankUsers().getBankDetails().getBankName().equals(bankName)).collect(Collectors.toList());
		if(userAccountDetails != null && !userAccountDetails.isEmpty()) {
			return userAccountDetails;
		}else {
			throw new BankingException("Account does not exist, Please create a new account");
		}
	}


	public List<TransactionDetailsEntity> getAllTransactions(int accountNumber) throws BankingException {
		List<TransactionDetailsEntity> txnList = txnDao.findAll();
		List<TransactionDetailsEntity> currentUserTxnList = txnList.stream().filter(txn -> txn.getAccountDetails().getAccountNumber() == accountNumber).collect(Collectors.toList());
		if(currentUserTxnList != null && !currentUserTxnList.isEmpty()) {
			return currentUserTxnList;
		}else {
			throw new BankingException("No transactions were done with the current account");
		}
	}

}
