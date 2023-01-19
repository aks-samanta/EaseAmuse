package com.EaseAmuse.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class CustomerOutputDTO {
	
	private Integer customerId;
	private String customerName;
	private String email;
	private String mobile;
	
}
