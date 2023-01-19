package com.EaseAmuse.payloads;

import lombok.Data;

@Data
public class CustomerOutputDTO {

	private Integer customerId;
	private String customerName;
	private String email;
	private String mobile;

}
