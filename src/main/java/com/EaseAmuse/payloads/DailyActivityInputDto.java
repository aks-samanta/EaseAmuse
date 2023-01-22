package com.EaseAmuse.payloads;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DailyActivityInputDto {

	private Integer slotsRemaining;
	private Date activityDate;
	private Integer activityId;
	
}
