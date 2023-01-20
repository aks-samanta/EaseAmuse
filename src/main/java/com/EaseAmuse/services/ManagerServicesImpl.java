package com.EaseAmuse.services;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.EaseAmuse.models.DailyActivity;
import com.EaseAmuse.models.Manager;
import com.EaseAmuse.repositories.DailyActivityRepo;
import com.EaseAmuse.repositories.ManagerRepo;

public class ManagerServicesImpl implements ManagerServices {
	
	@Autowired
	ManagerRepo mRepo;
	
	@Autowired
	DailyActivityRepo dRepo;

	@Override
	public Manager insertManager(Manager manager) {
	
		return mRepo.save(manager);
	}

	@Override
	public Manager updateManager(Manager manager) {
		
		Optional<Manager> opt = mRepo.findById(manager.getManagerId());
		
		if(opt.isPresent()) {
			
			Manager man = opt.get();
			
			Manager updated = mRepo.save(man);
			
			return updated;
		}
		return null;
	}

	@Override
	public Manager deleteManager(int mangerId) {
		
		Optional<Manager> opt = mRepo.findById(mangerId);
		
		if(opt.isPresent()) {
			
			Manager man = opt.get();
			
			 mRepo.delete(man);
			 
			 return man;
		}
		
		return null;
	}

	@Override
	public List<DailyActivity> getAllDailyActivities() {
		
		return dRepo.findAll();
	}

	@Override
	public List<DailyActivity> getDailyActivitiesCustomerwise(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DailyActivity> getDailyActivitiesDatewise(Date activityDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DailyActivity> getAllActivitiesForDays(int customerId, LocalDateTime fromDate, LocalDateTime toDate) {
		// TODO Auto-generated method stub
		return null;
	}



}
