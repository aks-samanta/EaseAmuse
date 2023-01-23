package com.EaseAmuse.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EaseAmuse.exceptions.UnauthorisedException;
import com.EaseAmuse.models.Booking;
import com.EaseAmuse.models.CurrentUserSession;
import com.EaseAmuse.models.UserType;
import com.EaseAmuse.payloads.AmusementParkOutputDto;
import com.EaseAmuse.payloads.BookingDto;
import com.EaseAmuse.payloads.CustomerInputDto;
import com.EaseAmuse.payloads.CustomerOutputDto;
import com.EaseAmuse.payloads.TicketInputDto;
import com.EaseAmuse.payloads.TicketOutputDto;
import com.EaseAmuse.services.AmusementParkServices;
import com.EaseAmuse.services.BookingServices;
import com.EaseAmuse.services.CustomerServices;
import com.EaseAmuse.services.SessionServices;
import com.EaseAmuse.services.TicketServices;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private CustomerServices customerServices;

	@Autowired
	private SessionServices sessionServices;

	@Autowired
	private BookingServices bookingServices;

	@Autowired
	private TicketServices ticketServices;

	@Autowired
	private AmusementParkServices parkServices; 
	
	@GetMapping("/amusementPark/{city}")
	public ResponseEntity<List<AmusementParkOutputDto>> getAmusementParksByCity(@Valid @RequestParam("Session") String uuid, @PathVariable("city") String city){
		CurrentUserSession currentUserSession = this.sessionServices.getSessionByKey(uuid);

		if (currentUserSession.getUserType() == UserType.CUSTOMER) {
			return new ResponseEntity<List<AmusementParkOutputDto>>(this.parkServices.getAmusementParksByCity(city), HttpStatus.FOUND);
		}
		else {
			throw new UnauthorisedException("Sorry you are not authorised.");

		}
	}
	
	@PostMapping("/customer")
	public ResponseEntity<CustomerOutputDto> registerCustomer(@Valid @RequestBody CustomerInputDto customerDTO) {

		return new ResponseEntity<CustomerOutputDto>(this.customerServices.registerCustomer(customerDTO),
				HttpStatus.CREATED);

	}

	@GetMapping("/customer/{customerId}")
	public ResponseEntity<CustomerOutputDto> getCustomerById(@PathVariable("customerId") Integer customerId) {

		return new ResponseEntity<CustomerOutputDto>(this.customerServices.getCustomerById(customerId),
				HttpStatus.FOUND);

	}

	@PutMapping("/customer/{customerId}")
	public ResponseEntity<CustomerOutputDto> updateCustomer(@Valid @RequestBody CustomerInputDto customerInputDTO,
			@PathVariable("customerId") Integer customerId) {

		return new ResponseEntity<CustomerOutputDto>(this.customerServices.updateCustomer(customerId, customerInputDTO),
				HttpStatus.OK);
	}

	@DeleteMapping("customer/{customerId}")
	public ResponseEntity<CustomerOutputDto> deleteCustomer(@Valid @RequestBody CustomerInputDto customerInputDTO,
			@PathVariable("customerId") Integer customerId) {

		return new ResponseEntity<CustomerOutputDto>(this.customerServices.deleteCustomer(customerId), HttpStatus.OK);
	}

	@GetMapping("/customer/booking/")
	public ResponseEntity<BookingDto> getBooking(@Valid @RequestParam("Session") String uuid) {

		CurrentUserSession currentUserSession = this.sessionServices.getSessionByKey(uuid);

		if (currentUserSession.getUserType() == UserType.CUSTOMER) {

			return new ResponseEntity<BookingDto>(this.bookingServices.createBooking(currentUserSession.getUserId()),
					HttpStatus.OK);
		} else {

			throw new UnauthorisedException("Sorry you are not authorised.");
		}

	}

	@GetMapping("/customer/allBookings")
	public ResponseEntity<List<BookingDto>> getAllBookingsOfCustomer(@Valid @RequestParam("Session") String uuid) {

		CurrentUserSession currentUserSession = this.sessionServices.getSessionByKey(uuid);

		if (currentUserSession.getUserType() == UserType.CUSTOMER) {

			return new ResponseEntity<List<BookingDto>>(
					this.bookingServices.getAllBookingsOfCustomer(currentUserSession.getUserId()), HttpStatus.OK);
		} else {

			throw new UnauthorisedException("Sorry you are not authorised.");
		}

	}

	@PutMapping("/bookings/cancelBooking")
	public ResponseEntity<BookingDto> cancelBooking(@Valid @RequestParam("Session") String uuid, Integer bookingId) {
		CurrentUserSession currentUserSession = this.sessionServices.getSessionByKey(uuid);

		if (currentUserSession.getUserType() == UserType.CUSTOMER) {

			return new ResponseEntity<BookingDto>(
					this.bookingServices.cancelBooking(bookingId, currentUserSession.getUserId()), HttpStatus.OK);
		} else {

			throw new UnauthorisedException("Sorry you are not authorised.");
		}

	}

	@PutMapping("/tickets/cancelTickets")
	public ResponseEntity<TicketOutputDto> cancelTicket(@Valid @RequestParam("Session") String uuid, Integer ticketId) {
		CurrentUserSession currentUserSession = this.sessionServices.getSessionByKey(uuid);

		if (currentUserSession.getUserType() == UserType.CUSTOMER) {

			return new ResponseEntity<TicketOutputDto>(
					this.ticketServices.cancelTicket(currentUserSession.getUserId(), ticketId), HttpStatus.OK);
		} else {

			throw new UnauthorisedException("Sorry you are not authorised.");
		}

	}

	@GetMapping("/customers/bookings/{bookingId}")
	public ResponseEntity<BookingDto> getBookingById(@Valid @RequestParam("Session") String uuid,
			@PathVariable Integer bookingId) {

		CurrentUserSession currentUserSession = this.sessionServices.getSessionByKey(uuid);

		if (currentUserSession.getUserType() == UserType.CUSTOMER) {

			return new ResponseEntity<BookingDto>(
					this.bookingServices.getBookingById(currentUserSession.getUserId(), bookingId), HttpStatus.OK);
		} else {

			throw new UnauthorisedException("Sorry you are not authorised.");
		}

	}

	@PostMapping("/tickets/")
	public ResponseEntity<TicketOutputDto> bookTicket(@Valid @RequestParam("Session") String uuid,
			@Valid @RequestBody TicketInputDto ticketDto) {
		CurrentUserSession currentUserSession = this.sessionServices.getSessionByKey(uuid);

		if (currentUserSession.getUserType() == UserType.CUSTOMER) {

			return new ResponseEntity<TicketOutputDto>(
					this.ticketServices.createTicket(currentUserSession.getUserId(), ticketDto), HttpStatus.CREATED);

		} else {

			throw new UnauthorisedException("Sorry you are not authorised.");
		}
	}

}
