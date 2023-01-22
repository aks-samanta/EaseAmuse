package com.EaseAmuse.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class ManagerInputDto {

	@NotEmpty
	@Size(min = 3, max = 30, message = "Manager name length should be 3 to 30 characters long")
	private String name;
	@Email
	private String email;
	@NotEmpty
	@Size(min = 10, max = 10, message = "Manager mobile length should be 10 characters long")
	private String mobile;
	@NotEmpty
	@Size(min = 8, max = 15, message = "Manager password length should be 8 to 15 characters long")
	private String password;

}
