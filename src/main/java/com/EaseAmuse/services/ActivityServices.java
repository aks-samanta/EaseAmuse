package com.EaseAmuse.services;

import java.util.List;

import com.EaseAmuse.exceptions.ResourceNotFoundException;
import com.EaseAmuse.payloads.ActivityInputDto;
import com.EaseAmuse.payloads.ActivityOutputDto;

public interface ActivityServices {

	// create
	ActivityOutputDto createActivity(Integer managerId, ActivityInputDto activityDto);

	// readAll
	List<ActivityOutputDto> getAllActivities();

	// readAllByCharges
	List<ActivityOutputDto> getActivitiesByCharges(Double charges) throws ResourceNotFoundException;

	// readAllByParkId
	List<ActivityOutputDto> getAllActivitiesOfPark(Integer parkId) throws ResourceNotFoundException;

	// readById
	ActivityOutputDto getActivityById(Integer Id) throws ResourceNotFoundException;

	// update
	ActivityOutputDto updateActivity(Integer managerId, Integer activityId, ActivityInputDto activityDto)
			throws ResourceNotFoundException;

	// delete
	ActivityOutputDto deleteActivity(Integer managerId, Integer activityId) throws ResourceNotFoundException;
}
