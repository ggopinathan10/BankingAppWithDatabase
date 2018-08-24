package com.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.business.BankEntity.TransactionDetailsEntity;

@Repository("txndao")
public interface BankTransactionRepository  extends JpaRepository<TransactionDetailsEntity, Long>{
	
}
