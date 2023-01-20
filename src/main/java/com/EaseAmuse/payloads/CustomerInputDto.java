package com.EaseAmuse.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CustomerInputDTO {

	@NotEmpty
	@Size(min = 3, max = 30, message = "Customer name length should be 3 to 30 characters long")
	private String customerName;
	@Email
	private String email;
	@NotEmpty
	@Size(min = 10, max = 10, message = "Customer mobile length should be 10 characters long")
	private String mobile;
	@NotEmpty
	@Size(min = 8, max = 15, message = "Customer password length should be 8 to 15 characters long")
	private String password;

}
