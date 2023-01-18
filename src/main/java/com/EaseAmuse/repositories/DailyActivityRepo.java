package com.EaseAmuse.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.EaseAmuse.models.DailyActivity;

public interface DailyActivityRepo extends JpaRepository<DailyActivity, Integer> {

	
	
}
