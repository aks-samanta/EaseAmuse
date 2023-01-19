package com.EaseAmuse.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOutputDto {

	private Integer customerId;
	private String customerName;
	private String email;
	private String mobile;

}
