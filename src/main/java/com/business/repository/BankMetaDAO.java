package com.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.business.BankEntity.BankDetailsEntity;

@Repository("bankMetaDAO")
public interface BankMetaDAO extends JpaRepository<BankDetailsEntity, Long>{

	public BankDetailsEntity findByBankName(String bankName); 
}
