package com.EaseAmuse.services;

import java.util.List;

import com.EaseAmuse.exceptions.ResourceNotFoundException;
import com.EaseAmuse.models.Customer;
import com.EaseAmuse.payloads.BookingDto;

public interface BookingServices {

	// create
	BookingDto createBooking(Integer customerId) throws ResourceNotFoundException;

	// readById
	BookingDto getBookingById(Integer customerId,Integer bookingId) throws ResourceNotFoundException;

	// readAll
	List<BookingDto> getAllBookingsOfCustomer(Integer customerId) throws ResourceNotFoundException;

	// update
	BookingDto cancelBooking(Integer bookingId, Integer customerId) throws ResourceNotFoundException;

}
