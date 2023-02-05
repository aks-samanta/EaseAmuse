package com.EaseAmuse.services;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EaseAmuse.exceptions.ResourceNotFoundException;
import com.EaseAmuse.models.Admin;
import com.EaseAmuse.payloads.ActivityInputDto;
import com.EaseAmuse.payloads.ActivityOutputDto;
import com.EaseAmuse.payloads.AdminInputDto;
import com.EaseAmuse.payloads.AdminOutputDto;
import com.EaseAmuse.payloads.AmusementParkInputDto;
import com.EaseAmuse.payloads.AmusementParkOutputDto;
import com.EaseAmuse.payloads.DailyActivityOutputDto;
import com.EaseAmuse.repositories.ActivityRepo;
import com.EaseAmuse.repositories.AdminRepo;
import com.EaseAmuse.repositories.AmusementParkRepo;
import com.EaseAmuse.repositories.CustomerRepo;
import com.EaseAmuse.repositories.DailyActivityRepo;

@Service
public class AdminServicesImpl implements AdminServices {

	@Autowired
	AdminRepo adminRepo;

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
	public AdminOutputDto insertAdmin(AdminInputDto adminInpDto) throws ResourceNotFoundException {

		Admin admin = this.modelMapper.map(adminInpDto, Admin.class);

		Admin savedAdmin = this.adminRepo.save(admin);

		return this.modelMapper.map(savedAdmin, AdminOutputDto.class);

	}

	@Override
	public AdminOutputDto updateAdmin(Integer adminId, AdminInputDto adminInpDto) throws ResourceNotFoundException {

		Admin foundAdmin = this.adminRepo.findById(adminId)
				.orElseThrow(() -> new ResourceNotFoundException("Admin", "adminId", adminId.toString()));

		foundAdmin.setName(adminInpDto.getName());
		foundAdmin.setEmail(adminInpDto.getEmail());
		foundAdmin.setMobile(adminInpDto.getMobile());
		foundAdmin.setPassword(adminInpDto.getPassword());

		Admin updatedAdmin = this.adminRepo.save(foundAdmin);

		return this.modelMapper.map(updatedAdmin, AdminOutputDto.class);

	}

	@Override
	public AdminOutputDto deleteAdmin(Integer adminId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AmusementParkOutputDto createAmusementPark(AmusementParkInputDto amusementParkInpDto)
			throws ResourceNotFoundException {

		return amusementParkServices.createAmusementPark(amusementParkInpDto);

	}

	@Override
	public List<DailyActivityOutputDto> getAllDailyActivities(Integer adminId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DailyActivityOutputDto> getDailyActivitiesCustomerwise(Integer customerId)
			throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DailyActivityOutputDto> getDailyActivitiesDatewise(Integer adminId, Date activityDate)
			throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActivityOutputDto> getAllActivities(Integer adminId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActivityOutputDto createActivity(Integer adminId, ActivityInputDto activityDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AmusementParkOutputDto getAmusementPark(Integer adminId) {
		// TODO Auto-generated method stub
		return null;
	}

}
