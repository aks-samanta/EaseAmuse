package com.EaseAmuse.payloads;



import com.EaseAmuse.models.Manager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AmusementParkOutputDto {
	private Integer parkId;
	private String parkName;
	private String city;
	private String managerName;
	private String managerMobile;
	
}
