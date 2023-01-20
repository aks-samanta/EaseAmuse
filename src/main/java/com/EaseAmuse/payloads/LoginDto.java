package com.EaseAmuse.payloads;

import com.EaseAmuse.models.UserType;

import lombok.Data;

@Data
public class LoginDto {

	private String email;
	private String password;
	private UserType userType;

}
