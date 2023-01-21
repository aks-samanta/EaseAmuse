package com.EaseAmuse.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class ManagerOutputDto {
	
	private Integer mangerId;
	private String mangerName;
	private String mangerEmail;
	private String mangerMobile;
	
}
