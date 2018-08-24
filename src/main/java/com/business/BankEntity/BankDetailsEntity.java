package com.business.BankEntity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="bank_details")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BankDetailsEntity {
	
	//Username (yes/no), Password (Yes/no), CorpId(Yes/No)]. 
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private int id;
	@Column(name="bank_name") private String bankName;
	
	public BankDetailsEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BankDetailsEntity(int id, String bankName) {
		this.id = id;
		this.bankName = bankName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	
	

	
}
