package com.EaseAmuse.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.EaseAmuse.exceptions.ResourceNotFoundException;
import com.EaseAmuse.exceptions.UnauthorisedException;
import com.EaseAmuse.models.Activity;
import com.EaseAmuse.models.AmusementPark;
import com.EaseAmuse.models.Manager;
import com.EaseAmuse.payloads.ActivityInputDto;
import com.EaseAmuse.payloads.ActivityOutputDto;
import com.EaseAmuse.repositories.ActivityRepo;
import com.EaseAmuse.repositories.AmusementParkRepo;
import com.EaseAmuse.repositories.ManagerRepo;

public class ActivityServicesImpl implements ActivityServices {

	@Autowired
	private ManagerRepo managerRepo;

	@Autowired
	private ActivityRepo activityRepo;

	@Autowired
	private AmusementParkRepo parkRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ActivityOutputDto createActivity(Integer managerId, ActivityInputDto activityDto) {

		Manager manager = this.managerRepo.findById(managerId)
				.orElseThrow(() -> new ResourceNotFoundException("Manager", "ManagerId", managerId.toString()));

		AmusementPark park = manager.getAmusementPark();

		Activity activity = this.modelMapper.map(activityDto, Activity.class);

		activity.setAmusementPark(park);
		park.getActivities().add(activity);

		AmusementPark updatedPark = this.parkRepo.save(park);

		Activity createdActivity = updatedPark.getActivities().get(updatedPark.getActivities().size() - 1);

		return this.modelMapper.map(createdActivity, ActivityOutputDto.class);

	}

	@Override
	public List<ActivityOutputDto> getAllActivities() {
		List<Activity> activities = this.activityRepo.findAll();

		List<ActivityOutputDto> activityDos = activities.stream()
				.map((a) -> this.modelMapper.map(a, ActivityOutputDto.class)).collect(Collectors.toList());

		return activityDos;
	}

	@Override
	public List<ActivityOutputDto> getActivitiesByCharges(Double charges) throws ResourceNotFoundException {

		List<Activity> activities = this.activityRepo.findByCharges(charges);

		List<ActivityOutputDto> activityDos = activities.stream()
				.map((a) -> this.modelMapper.map(a, ActivityOutputDto.class)).collect(Collectors.toList());

		return activityDos;
	}

	@Override
	public List<ActivityOutputDto> getAllActivitiesOfPark(Integer parkId) throws ResourceNotFoundException {

		AmusementPark park = this.parkRepo.findById(parkId)
				.orElseThrow(() -> new ResourceNotFoundException("AmusementPark", "park Id", parkId.toString()));

		List<Activity> activities = this.activityRepo.findByAmusementPark(park);

		List<ActivityOutputDto> activityDos = activities.stream()
				.map((a) -> this.modelMapper.map(a, ActivityOutputDto.class)).collect(Collectors.toList());

		return activityDos;
	}

	@Override
	public ActivityOutputDto getActivityById(Integer Id) throws ResourceNotFoundException {

		Activity activity = this.activityRepo.findById(Id)
				.orElseThrow(() -> new ResourceNotFoundException("Activity", "Activity Id", Id.toString()));

		return this.modelMapper.map(activity, ActivityOutputDto.class);

	}

	@Override
	public ActivityOutputDto updateActivity(Integer managerId, Integer activityId, ActivityInputDto activityDto)
			throws ResourceNotFoundException {

		Activity activity = this.activityRepo.findById(activityId)
				.orElseThrow(() -> new ResourceNotFoundException("Activity", "activity Id", activityId.toString()));

		if (activity.getAmusementPark().getManager().getManagerId() == managerId) {
			activity.setCharges(activityDto.getCharges());
			activity.setName(activityDto.getName());
			activity.setDescription(activityDto.getDescription());

			Activity savedActivity = this.activityRepo.save(activity);

			return this.modelMapper.map(savedActivity, ActivityOutputDto.class);
		} else {
			throw new UnauthorisedException("You are not authorised to update this Activity!");
		}
	}

	@Override
	public ActivityOutputDto deleteActivity(Integer managerId, Integer activityId) throws ResourceNotFoundException {

		Activity activity = this.activityRepo.findById(activityId)
				.orElseThrow(() -> new ResourceNotFoundException("Activity", "activity Id", activityId.toString()));

		if (activity.getAmusementPark().getManager().getManagerId() == managerId) {

			this.activityRepo.delete(activity);

			return this.modelMapper.map(activity, ActivityOutputDto.class);
		} else {
			throw new UnauthorisedException("You are not authorised to delete this Activity!");
		}
	}

}
