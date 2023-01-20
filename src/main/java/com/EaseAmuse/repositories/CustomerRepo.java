package com.EaseAmuse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EaseAmuse.models.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{

	
}
