package com.business.BankEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BANK_NAMES")
public class BankNamesEntity {

	@Id @Column(name="bank_id") private int id;
	@Column(name="bank_name") private String bankNames;
	
	public BankNamesEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BankNamesEntity(int id, String bankNames) {
		this.id = id;
		this.bankNames = bankNames;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBankNames() {
		return bankNames;
	}

	public void setBankNames(String bankNames) {
		this.bankNames = bankNames;
	}
	
	
}
