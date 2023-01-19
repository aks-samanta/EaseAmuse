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
public class DailyActivityOutputDto {

	private Integer dailyActivityId;
	private Integer slotsRemaining;
	private Date activityDate;
	private String activityName;
	private Double activityCharges;
	private String amusementParkName;
	private String amusementParkCity;

}
