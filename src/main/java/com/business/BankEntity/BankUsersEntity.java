package com.business.BankEntity;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="bank_user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BankUsersEntity {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;
	@Column(name = "username") private String username;
	@Column(name = "password") private String password;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "corp_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private BankDetailsEntity bankDetails;
	
	public BankUsersEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BankUsersEntity(int id, String username, String password, BankDetailsEntity bankDetails) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.bankDetails = bankDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BankDetailsEntity getBankDetails() {
		return bankDetails;
	}

	public void setBankDetails(BankDetailsEntity bankDetails) {
		this.bankDetails = bankDetails;
	}

	
	
}
