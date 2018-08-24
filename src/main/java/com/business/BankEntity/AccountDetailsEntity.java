package com.business.BankEntity;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="account_details")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AccountDetailsEntity {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private int id;
	@Column(name="account_number") private int accountNumber;
	@Column(name="account_Type") private String accountType;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private BankUsersEntity bankUsers;
	
	public AccountDetailsEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountDetailsEntity(int id, int accountNumber, String accountType, BankUsersEntity bankUsers) {
		this.id = id;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.bankUsers = bankUsers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public BankUsersEntity getBankUsers() {
		return bankUsers;
	}

	public void setBankUsers(BankUsersEntity bankUsers) {
		this.bankUsers = bankUsers;
	}
	
	
	
}
