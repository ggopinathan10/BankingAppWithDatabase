package com.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.business.BankEntity.BankUsersEntity;

@Repository("userdao")
public interface UserDAO extends JpaRepository<BankUsersEntity, Long>{	
		
	public BankUsersEntity findByUsername(String username);
	
	public List<BankUsersEntity> findAllByUsername(String username);
	
}
