package com.EaseAmuse.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AdminInputDto {

	@NotEmpty
	@Size(min = 3, max = 30, message = "Admin name length should be 3 to 30 characters long")
	private String name;
	@Email
	private String email;
	@NotEmpty
	@Size(min = 10, max = 10, message = "Admin mobile length should be 10 characters long")
	private String mobile;
	@NotEmpty
	@Size(min = 8, max = 15, message = "Admin password length should be 8 to 15 characters long")
	private String password;

}
