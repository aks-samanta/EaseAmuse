package com.EaseAmuse.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EaseAmuse.exceptions.CustomerException;
import com.EaseAmuse.exceptions.ResourceNotFoundException;
import com.EaseAmuse.models.Activity;
import com.EaseAmuse.models.AmusementPark;
import com.EaseAmuse.models.Customer;
import com.EaseAmuse.models.DailyActivity;
import com.EaseAmuse.models.Manager;
import com.EaseAmuse.payloads.ActivityInputDto;
import com.EaseAmuse.payloads.ActivityOutputDto;
import com.EaseAmuse.payloads.AmusementParkInputDto;
import com.EaseAmuse.payloads.AmusementParkOutputDto;
import com.EaseAmuse.payloads.CustomerOutputDto;
import com.EaseAmuse.payloads.DailyActivityOutputDto;
import com.EaseAmuse.payloads.ManagerInputDto;
import com.EaseAmuse.payloads.ManagerOutputDto;
import com.EaseAmuse.repositories.ActivityRepo;
import com.EaseAmuse.repositories.AmusementParkRepo;
import com.EaseAmuse.repositories.CustomerRepo;
import com.EaseAmuse.repositories.DailyActivityRepo;
import com.EaseAmuse.repositories.ManagerRepo;

@Service
public class ManagerServicesImpl implements ManagerServices {

	@Autowired
	ManagerRepo managerRepo;

	@Autowired
	DailyActivityRepo dailyActivityRepo;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	AmusementParkServices amusementParkServices;

	@Autowired
	AmusementParkRepo parkRepo;

	@Autowired
	CustomerRepo customerRepo;

	@Autowired
	ActivityRepo activityRepo;

	@Override
	public ManagerOutputDto insertManager(ManagerInputDto managerInpDto) throws ResourceNotFoundException {

		Manager manager = this.modelMapper.map(managerInpDto, Manager.class);

		Manager savedManager = this.managerRepo.save(manager);

		return this.modelMapper.map(savedManager, ManagerOutputDto.class);

	}

	@Override
	public ManagerOutputDto updateManager(Integer managerId, ManagerInputDto managerInpDto)
			throws ResourceNotFoundException {

		Manager foundManager = this.managerRepo.findById(managerId)
				.orElseThrow(() -> new ResourceNotFoundException("Manager", "managerId", managerId.toString()));

		foundManager.setName(managerInpDto.getName());
		foundManager.setEmail(managerInpDto.getEmail());
		foundManager.setMobile(managerInpDto.getMobile());
		foundManager.setPassword(managerInpDto.getPassword());

		Manager updatedManager = this.managerRepo.save(foundManager);

		return this.modelMapper.map(updatedManager, ManagerOutputDto.class);
	}

	@Override
	public ManagerOutputDto deleteManager(Integer managerId) throws ResourceNotFoundException {

		Manager foundManager = this.managerRepo.findById(managerId)
				.orElseThrow(() -> new ResourceNotFoundException("Manager", "managerId", managerId.toString()));

		this.managerRepo.delete(foundManager);
		return this.modelMapper.map(foundManager, ManagerOutputDto.class);
	}

	@Override
	public AmusementParkOutputDto createAmusementPark(AmusementParkInputDto amusementParkInpDto)
			throws ResourceNotFoundException {

		return amusementParkServices.createAmusementPark(amusementParkInpDto);

	}

	@Override
	public List<DailyActivityOutputDto> getAllDailyActivities(Integer managerId) throws ResourceNotFoundException {

		Manager manager = this.managerRepo.findById(managerId)
				.orElseThrow(() -> new ResourceNotFoundException("Manager", "Manager Id", managerId.toString()));

		List<DailyActivity> dailyActivities = dailyActivityRepo.findByAmusementPark(manager.getAmusementPark());

		return dailyActivities.stream().map((da) -> this.modelMapper.map(da, DailyActivityOutputDto.class))
				.collect(Collectors.toList());

	}

	@Override
	public List<DailyActivityOutputDto> getDailyActivitiesCustomerwise(Integer customerId)
			throws ResourceNotFoundException {

		List<DailyActivityOutputDto> activities = new ArrayList<>();

		Customer customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));

		customer.getBookings().forEach(b -> b.getTickets().forEach(
				t -> activities.add(this.modelMapper.map(t.getDailyActivity(), DailyActivityOutputDto.class))));

		return activities;

	}

	@Override
	public List<DailyActivityOutputDto> getDailyActivitiesDatewise(Integer managerId, Date activityDate)
			throws ResourceNotFoundException {

		Manager manager = this.managerRepo.findById(managerId)
				.orElseThrow(() -> new ResourceNotFoundException("Manager", "Manager Id", managerId.toString()));

		List<DailyActivity> activities = dailyActivityRepo
				.findByAmusementParkAndActivityDate(manager.getAmusementPark(), activityDate);

		return activities.stream().map((da) -> this.modelMapper.map(da, DailyActivityOutputDto.class))
				.collect(Collectors.toList());

	}

	@Override
	public List<ActivityOutputDto> getAllActivities(Integer managerId) throws ResourceNotFoundException {
		Manager manager = this.managerRepo.findById(managerId)
				.orElseThrow(() -> new ResourceNotFoundException("Manager", "Manager Id", managerId.toString()));

		List<Activity> activities = manager.getAmusementPark().getActivities();

		return activities.stream().map((a) -> this.modelMapper.map(a, ActivityOutputDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ActivityOutputDto createActivity(Integer managerId, ActivityInputDto activityDto) {

		Manager manager = this.managerRepo.findById(managerId)
				.orElseThrow(() -> new ResourceNotFoundException("Manager", "Manager Id", managerId.toString()));

		AmusementPark park = manager.getAmusementPark();

		Activity activity = this.modelMapper.map(activityDto, Activity.class);
		activity.setAmusementPark(park);
		park.getActivities().add(activity);

		AmusementPark updatedPark = this.parkRepo.save(park);

		return this.modelMapper.map(updatedPark.getActivities().get((updatedPark.getActivities().size() - 1)),
				ActivityOutputDto.class);
	}

	@Override
	public AmusementParkOutputDto getAmusementPark(Integer managerId) {

		Manager manager = this.managerRepo.findById(managerId)
				.orElseThrow(() -> new ResourceNotFoundException("Manager", "Manager Id", managerId.toString()));

		return this.modelMapper.map(manager.getAmusementPark(), AmusementParkOutputDto.class);

	}

}
