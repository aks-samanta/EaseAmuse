package com.EaseAmuse.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EaseAmuse.exceptions.LoginException;
import com.EaseAmuse.models.CurrentUserSession;
import com.EaseAmuse.models.UserType;
import com.EaseAmuse.payloads.LoginDto;
import com.EaseAmuse.services.LoginService;


@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping("/login")
	public ResponseEntity<CurrentUserSession> logInHandler(@Valid @RequestBody LoginDto loginDto)
			throws LoginException {

		CurrentUserSession userSession = loginService.logIn(loginDto);

		return new ResponseEntity<CurrentUserSession>(userSession, HttpStatus.ACCEPTED);

	}

	@PostMapping("/logout")
	public ResponseEntity<String> logOutHandler(@RequestParam(name = "userId") Integer userId,
			@RequestParam(name = "userType") UserType userType) throws LoginException {

		String msg = loginService.logOut(userId, userType);

		return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);

	}

}
