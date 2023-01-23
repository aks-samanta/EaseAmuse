package com.EaseAmuse.services;

import java.util.Date;
import java.util.List;

import com.EaseAmuse.exceptions.ResourceNotFoundException;
import com.EaseAmuse.payloads.ActivityInputDto;
import com.EaseAmuse.payloads.ActivityOutputDto;
import com.EaseAmuse.payloads.AdminInputDto;
import com.EaseAmuse.payloads.AdminOutputDto;
import com.EaseAmuse.payloads.AmusementParkInputDto;
import com.EaseAmuse.payloads.AmusementParkOutputDto;
import com.EaseAmuse.payloads.DailyActivityOutputDto;


public interface AdminServices {
	
	public AdminOutputDto insertAdmin(AdminInputDto adminInpDto) throws ResourceNotFoundException;

	public AdminOutputDto updateAdmin(Integer adminId, AdminInputDto adminInpDto)
			throws ResourceNotFoundException;

	public AdminOutputDto deleteAdmin(Integer adminId) throws ResourceNotFoundException;

	public AmusementParkOutputDto createAmusementPark(AmusementParkInputDto amusementParkInpDto)
			throws ResourceNotFoundException;

	public List<DailyActivityOutputDto> getAllDailyActivities(Integer adminId) throws ResourceNotFoundException;

	public List<DailyActivityOutputDto> getDailyActivitiesCustomerwise( Integer customerId)
			throws ResourceNotFoundException;

	public List<DailyActivityOutputDto> getDailyActivitiesDatewise(Integer adminId, Date activityDate) throws ResourceNotFoundException;
	
	
	public List<ActivityOutputDto> getAllActivities(Integer adminId) throws ResourceNotFoundException;

	
	public ActivityOutputDto createActivity(Integer adminId, ActivityInputDto activityDto);
	

	public AmusementParkOutputDto getAmusementPark(Integer adminId);
	
	

}
