package com.EaseAmuse.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EaseAmuse.exceptions.UnauthorisedException;
import com.EaseAmuse.models.CurrentUserSession;
import com.EaseAmuse.models.UserType;
import com.EaseAmuse.payloads.ActivityInputDto;
import com.EaseAmuse.payloads.ActivityOutputDto;
import com.EaseAmuse.payloads.AmusementParkInputDto;
import com.EaseAmuse.payloads.AmusementParkOutputDto;
import com.EaseAmuse.payloads.DailyActivityInputDto;
import com.EaseAmuse.payloads.DailyActivityOutputDto;
import com.EaseAmuse.payloads.ManagerInputDto;
import com.EaseAmuse.payloads.ManagerOutputDto;
import com.EaseAmuse.repositories.SessionRepo;
import com.EaseAmuse.services.ActivityServices;
import com.EaseAmuse.services.AmusementParkServices;
import com.EaseAmuse.services.ManagerServices;

@RestController
@RequestMapping("/managers")
public class ManagerController {

	@Autowired
	private ManagerServices managerServices;

	@Autowired
	private ActivityServices activityServices;

	@Autowired
	private AmusementParkServices parkServices;
	
	@Autowired
	private SessionRepo sessionRepo;
	

	@PostMapping("/")
	public ResponseEntity<ManagerOutputDto> createManager(@Valid @RequestBody ManagerInputDto managerDto) {

		return new ResponseEntity<>(this.managerServices.insertManager(managerDto), HttpStatus.CREATED);

	}

	@PostMapping("/amusementPark/")
	public ResponseEntity<AmusementParkOutputDto> createAmusementPark(
			@Valid @RequestBody AmusementParkInputDto parkDto) {

		return new ResponseEntity<>(this.managerServices.createAmusementPark(parkDto), HttpStatus.CREATED);

	}

	@PutMapping("/")
	public ResponseEntity<ManagerOutputDto> updateManager(@Valid @RequestBody ManagerInputDto managerDto) {
		Integer managerId = 0;
		return new ResponseEntity<>(this.managerServices.updateManager(managerId, managerDto), HttpStatus.OK);
	}

	@DeleteMapping("/")
	public ResponseEntity<ManagerOutputDto> deleteManager() {
		Integer managerId = 0;
		return new ResponseEntity<>(this.managerServices.deleteManager(managerId), HttpStatus.OK);
	}

	@PostMapping("/dailyActivity/")
	public ResponseEntity<DailyActivityOutputDto> createDailyActivity (@Valid @RequestBody DailyActivityInputDto dailyActivityInputDto,){
		
	}
	
	@GetMapping("/dailyActivities/")
	public ResponseEntity<List<DailyActivityOutputDto>> getAllDailyActivities(@RequestParam("session") String uuid) {
		Integer managerId = 2;
		CurrentUserSession currentUserSession = this.sessionRepo.findBySessionKey(uuid);
		if(currentUserSession.getUserType() == UserType.MANAGER) {
			return new ResponseEntity<>(this.managerServices.getAllDailyActivities(currentUserSession.getUserId()), HttpStatus.OK);
		}
		else {
			throw new UnauthorisedException("Sorry ! You are not authorised to access this method!!");
		}
		
	}

	@GetMapping("/activities/")
	public ResponseEntity<List<ActivityOutputDto>> getAllActivities() {
		Integer managerId = 2;
		return new ResponseEntity<List<ActivityOutputDto>>(this.managerServices.getAllActivities(managerId),
				HttpStatus.FOUND);
	}

	@PostMapping("/activities/")
	public ResponseEntity<ActivityOutputDto> addActivity(ActivityInputDto activityDto) {
		Integer managerId = 2;
		return new ResponseEntity<ActivityOutputDto>(this.managerServices.createActivity(managerId, activityDto),
				HttpStatus.CREATED);
	}

	@GetMapping("/amusementPark/")
	public ResponseEntity<AmusementParkOutputDto> getAmusementPark() {
		Integer managerid = 2;
		return new ResponseEntity<AmusementParkOutputDto>(this.managerServices.getAmusementPark(managerid),
				HttpStatus.FOUND);
	}

}
