package com.bci.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bci.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	
	@Query(value = "SELECT * FROM USERS WHERE EMAIL = ?1", nativeQuery = true)
	Optional<UserEntity> findByEmail(String email);
}
