package com.EaseAmuse.payloads;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CustomerOutputDto {

	private Integer customerId;
	private String customerName;
	private String email;
	private String mobile;

}
