package com.EaseAmuse.payloads;

import com.EaseAmuse.models.TicketStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketOutputDto {

	private Integer ticketId;
	private Double amount;
	private Integer noOfPerson;
	private Integer dailyActivitiesId;
	private String dailyActivityName;
	private TicketStatus ticketStatus;
}
