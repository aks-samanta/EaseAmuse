package com.EaseAmuse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EaseAmuse.models.Manager;

public interface ManagerRepo extends JpaRepository<Manager, Integer> {

}
