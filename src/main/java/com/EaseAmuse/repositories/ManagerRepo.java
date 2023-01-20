package com.EaseAmuse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EaseAmuse.models.Manager;

@Repository
public interface ManagerRepo extends JpaRepository<Manager, Integer> {
	
	

}
