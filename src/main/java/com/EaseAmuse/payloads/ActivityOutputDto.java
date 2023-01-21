package com.EaseAmuse.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityOutputDto {
	
	private Integer activityId;
	private String name;
	private String description;
	private Double charges;
	private Integer amusementParkId;
	private String amusementParkName;
	private String amusementParkCity;
}
