package com.EaseAmuse.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EaseAmuse.models.CurrentUserSession;
import com.EaseAmuse.payloads.AmusementParkInputDto;
import com.EaseAmuse.payloads.AmusementParkOutputDto;
import com.EaseAmuse.payloads.DailyActivityOutputDto;
import com.EaseAmuse.payloads.ManagerInputDto;
import com.EaseAmuse.payloads.ManagerOutputDto;
import com.EaseAmuse.services.AmusementParkServices;
import com.EaseAmuse.services.ManagerServices;

@RestController
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private ManagerServices managerServices;

	@Autowired
	private AmusementParkServices parkServices;

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

	@GetMapping("/dailyActivities/")
	public ResponseEntity<List<DailyActivityOutputDto>> getAllDailyActivities() {
		Integer managerId = 0;
		return new ResponseEntity<>(this.managerServices.getAllDailyActivities(managerId), HttpStatus.OK);
	}

}
