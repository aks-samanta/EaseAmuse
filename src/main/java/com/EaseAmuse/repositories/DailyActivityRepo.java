package com.EaseAmuse.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EaseAmuse.models.AmusementPark;
import com.EaseAmuse.models.DailyActivity;

public interface DailyActivityRepo extends JpaRepository<DailyActivity, Integer> {

	List<DailyActivity> findByAmusementPark(AmusementPark amusementPark);
	
}
