package com.EaseAmuse.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.EaseAmuse.models.Customer;
import com.EaseAmuse.models.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Integer> {

	// to be used in bookingServiceImpl
	@Query("SELECT t from Ticket t WHERE t.customer = :customer AND t.ticketStatus = 'PENDING'")
	List<Ticket> findByCustomerAndTicketStatus(@Param("customer") Customer customer);

}
