package com.business.BankEntity;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="txn_details")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TransactionDetailsEntity {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private int id;
	@Column(name="txnDate")	private Date time;
	@Column(name="txnAmount") private double transactionAmount;
	@Column(name="txnType")	private String transactionType;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private AccountDetailsEntity accountDetails;

	public TransactionDetailsEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransactionDetailsEntity(int id, Date time, double transactionAmount, String transactionType,
			AccountDetailsEntity accountDetails) {
		this.id = id;
		this.time = time;
		this.transactionAmount = transactionAmount;
		this.transactionType = transactionType;
		this.accountDetails = accountDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public AccountDetailsEntity getAccountDetails() {
		return accountDetails;
	}

	public void setAccountDetails(AccountDetailsEntity accountDetails) {
		this.accountDetails = accountDetails;
	}

	
}
