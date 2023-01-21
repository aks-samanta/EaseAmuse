package com.EaseAmuse.repositories;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EaseAmuse.models.Activity;
import com.EaseAmuse.models.AmusementPark;
import com.EaseAmuse.models.DailyActivity;

public interface DailyActivityRepo extends JpaRepository<DailyActivity, Integer> {

	List<DailyActivity> findByAmusementPark(AmusementPark amusementPark);

	List<DailyActivity> findByActivityDate(Date activityDate);

	List<DailyActivity> findByActivity(Activity activity);

	List<DailyActivity> findByAmusementParkAndActivityDate(AmusementPark park, Date date);
}
