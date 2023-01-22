package com.EaseAmuse.services;

import com.EaseAmuse.exceptions.ResourceNotFoundException;
import com.EaseAmuse.models.Ticket;
import com.EaseAmuse.payloads.TicketInputDto;
import com.EaseAmuse.payloads.TicketOutputDto;

public interface TicketServices {

	TicketOutputDto createTicket(Integer customerId, TicketInputDto ticketDto) throws ResourceNotFoundException;

	TicketOutputDto cancelTicket(Integer customerId,Integer ticketId) throws ResourceNotFoundException;

}
