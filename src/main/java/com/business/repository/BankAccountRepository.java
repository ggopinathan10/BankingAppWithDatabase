package com.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.business.BankEntity.AccountDetailsEntity;

@Repository("accountdao")
public interface BankAccountRepository extends JpaRepository<AccountDetailsEntity, Long>{

	public AccountDetailsEntity findByAccountNumber(int accountNumber);
}
