package com.bci.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bci.entity.PhoneEntity;

public interface PhoneRepository extends JpaRepository<PhoneEntity, Integer>{

}
