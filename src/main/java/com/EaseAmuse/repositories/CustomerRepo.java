package com.EaseAmuse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EaseAmuse.models.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer>{

}
