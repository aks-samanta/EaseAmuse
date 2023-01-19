package com.EaseAmuse.payloads;

import lombok.Data;

@Data
public class CustomerOutputDto {

	private Integer customerId;
	private String customerName;
	private String email;
	private String mobile;

}
