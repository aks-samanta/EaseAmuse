package com.EaseAmuse.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.EaseAmuse.models.Customer;
import com.EaseAmuse.models.Ticket;
import com.EaseAmuse.models.TicketStatus;

public interface TicketRepo extends JpaRepository<Ticket, Integer> {

	// to be used in bookingServiceImpl
	List<Ticket> findByCustomerAndTicketStatus(Customer customer, TicketStatus ts);

}
