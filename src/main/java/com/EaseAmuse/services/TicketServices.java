package com.EaseAmuse.services;

import com.EaseAmuse.exceptions.ResourceNotFoundException;
import com.EaseAmuse.models.Ticket;

public interface TicketServices {

	Ticket cancelTicket(Integer ticketId) throws ResourceNotFoundException;
	
	Ticket cancelTicket(Ticket ticket) throws ResourceNotFoundException;

}
