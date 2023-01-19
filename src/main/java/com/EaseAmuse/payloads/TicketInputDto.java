package com.EaseAmuse.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketInputDto {

	private Integer dailyActivityId;
	private Integer noOfPerson;

}
