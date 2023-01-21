package com.EaseAmuse.services;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.EaseAmuse.exceptions.ResourceNotFoundException;
import com.EaseAmuse.models.AmusementPark;
import com.EaseAmuse.models.DailyActivity;
import com.EaseAmuse.models.Manager;
import com.EaseAmuse.payloads.AmusementParkInputDto;
import com.EaseAmuse.payloads.AmusementParkOutputDto;
import com.EaseAmuse.payloads.DailyActivityOutputDto;
import com.EaseAmuse.payloads.ManagerInputDto;
import com.EaseAmuse.payloads.ManagerOutputDto;

public interface ManagerServices {
	
	public ManagerOutputDto insertManager(ManagerInputDto managerInpDto) throws ResourceNotFoundException;
	
	public ManagerOutputDto updateManager(Integer managerId ,ManagerInputDto managerInpDto)throws ResourceNotFoundException;
	
	public ManagerOutputDto deleteManager(Integer managerId)throws ResourceNotFoundException;
	
	public AmusementParkOutputDto createAmusementPark(AmusementParkInputDto amusementParkInpDto)throws ResourceNotFoundException;
	
	public List<DailyActivityOutputDto> getAllDailyActivities()throws ResourceNotFoundException;
	
	public List<DailyActivityOutputDto> getDailyActivitiesCustomerwise(Integer customerId)throws ResourceNotFoundException;
	
	public List<DailyActivityOutputDto> getDailyActivitiesDatewise(Date activityDate)throws ResourceNotFoundException;
	
	public List<DailyActivityOutputDto> getAllActivitiesForDays(Integer customerId,Date fromDate,Date toDate)throws ResourceNotFoundException;

}
