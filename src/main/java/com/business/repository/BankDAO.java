package com.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.business.BankEntity.BankDetailsEntity;
import com.business.BankEntity.BankNamesEntity;

@Repository("bankdao")
public interface BankDAO extends JpaRepository<BankNamesEntity, Long>{

}
