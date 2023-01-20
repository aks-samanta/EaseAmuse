package com.EaseAmuse.services;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.EaseAmuse.models.DailyActivity;
import com.EaseAmuse.models.Manager;

public interface ManagerServices {
	
	public Manager insertManager(Manager manager);
	
	public Manager updateManager(Manager manager);
	
	public Manager deleteManager(int mangerId);
	
	public List<DailyActivity> getAllDailyActivities();
	
	public List<DailyActivity> getDailyActivitiesCustomerwise(int customerId);
	
	public List<DailyActivity> getDailyActivitiesDatewise(Date activityDate);
	
	public List<DailyActivity> getAllActivitiesForDays(int customerId,LocalDateTime fromDate,LocalDateTime toDate);

}
