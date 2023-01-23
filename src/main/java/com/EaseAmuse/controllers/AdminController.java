package com.EaseAmuse.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EaseAmuse.exceptions.UnauthorisedException;
import com.EaseAmuse.models.CurrentUserSession;
import com.EaseAmuse.models.UserType;
import com.EaseAmuse.payloads.AdminInputDto;
import com.EaseAmuse.payloads.AdminOutputDto;
import com.EaseAmuse.payloads.AmusementParkInputDto;
import com.EaseAmuse.payloads.AmusementParkOutputDto;
import com.EaseAmuse.payloads.ManagerInputDto;
import com.EaseAmuse.payloads.ManagerOutputDto;
import com.EaseAmuse.services.AdminServices;
import com.EaseAmuse.services.ManagerServices;
import com.EaseAmuse.services.SessionServices;

@RestController
@RequestMapping("/admins")
public class AdminController {

	@Autowired
	private AdminServices adminServices;

	@Autowired
	private SessionServices sessionServices;

	@Autowired
	private ManagerServices managerServices;

	@PostMapping("/")
	public ResponseEntity<AdminOutputDto> createManager(@Valid @RequestBody AdminInputDto adminDto) {

		return new ResponseEntity<>(this.adminServices.insertAdmin(adminDto), HttpStatus.CREATED);

	}

	@PutMapping("/")
	public ResponseEntity<AdminOutputDto> updateAdmin(@RequestParam("session") String uuid,
			@Valid @RequestBody AdminInputDto adminDto) {

		CurrentUserSession currentUserSession = this.sessionServices.getSessionByKey(uuid);

		if (currentUserSession.getUserType() == UserType.ADMIN) {
			return new ResponseEntity<>(this.adminServices.updateAdmin(currentUserSession.getUserId(), adminDto),
					HttpStatus.OK);
		} else {
			throw new UnauthorisedException("Sorry ! You are not authorised to access this method!!");
		}

	}

	@PostMapping("/manager")
	public ResponseEntity<ManagerOutputDto> createManager(@Valid @RequestBody ManagerInputDto managerDto,
			@RequestParam("session") String uuid) {

		CurrentUserSession currentUserSession = this.sessionServices.getSessionByKey(uuid);

		if (currentUserSession.getUserType() == UserType.ADMIN) {
			return new ResponseEntity<>(this.managerServices.insertManager(managerDto), HttpStatus.CREATED);
		} else {
			throw new UnauthorisedException("Sorry ! You are not authorised to access this method!!");
		}

	}

	@PostMapping("/amusementPark/")
	public ResponseEntity<AmusementParkOutputDto> createAmusementPark(@Valid @RequestBody AmusementParkInputDto parkDto,
			@RequestParam("session") String uuid) {

		CurrentUserSession currentUserSession = this.sessionServices.getSessionByKey(uuid);

		if (currentUserSession.getUserType() == UserType.ADMIN) {
			return new ResponseEntity<>(this.adminServices.createAmusementPark(parkDto), HttpStatus.CREATED);
		} else {
			throw new UnauthorisedException("Sorry ! You are not authorised to access this method!!");
		}

	}

}
