package com.EaseAmuse.payloads;

import com.EaseAmuse.models.UserType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

	private String email;
	private String password;
	private UserType userType;

}
