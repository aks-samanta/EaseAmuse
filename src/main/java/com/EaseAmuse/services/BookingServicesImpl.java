package com.EaseAmuse.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.EaseAmuse.exceptions.ResourceNotFoundException;
import com.EaseAmuse.exceptions.UnauthorisedException;
import com.EaseAmuse.models.Booking;
import com.EaseAmuse.models.BookingStatus;
import com.EaseAmuse.models.Customer;
import com.EaseAmuse.models.Ticket;
import com.EaseAmuse.models.TicketStatus;
import com.EaseAmuse.payloads.BookingDto;
import com.EaseAmuse.payloads.TicketOutputDto;
import com.EaseAmuse.repositories.BookingRepo;
import com.EaseAmuse.repositories.CustomerRepo;
import com.EaseAmuse.repositories.TicketRepo;

public class BookingServicesImpl implements BookingServices {

	@Autowired
	private BookingRepo bookingRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private TicketRepo ticketRepo;

	@Autowired
	private TicketServices ticketService;

	@Override
	public BookingDto createBooking(Integer customerId) throws ResourceNotFoundException {

		Customer customer = this.customerRepo.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", customerId.toString()));

		Booking booking = new Booking();

		List<Ticket> tickets = this.ticketRepo.findByCustomerAndTicketStatus(customer, TicketStatus.PENDING);
		Double totalPrice = 0.00;
		for (Ticket ticket : tickets) {
			totalPrice += ticket.getAmount();
		}
		booking.setTotalPrice(totalPrice);
		booking.setTickets(tickets);
		booking.setCustomer(customer);
		customer.getBookings().add(booking);
		booking.setBookingStatus(BookingStatus.CONFIRMED);
		booking.setBookingDateTime(LocalDateTime.now());

		Booking newBooking = this.bookingRepo.save(booking);

		return this.bookingToDto(newBooking);
	}

	@Override
	public BookingDto getBookingById(Integer bookingId) throws ResourceNotFoundException {
		Booking booking = this.bookingRepo.findById(bookingId)
				.orElseThrow(() -> new ResourceNotFoundException("Booking", "Id", bookingId.toString()));

		List<TicketOutputDto> ticketDtos = booking.getTickets().stream()
				.map((t) -> this.modelMapper.map(t, TicketOutputDto.class)).collect(Collectors.toList());

		BookingDto bookingDto = this.bookingToDto(booking);
		bookingDto.setTicketDtos(ticketDtos);

		return bookingDto;

	}

	@Override
	public List<BookingDto> getAllBookingsOfCustomer(Integer customerId) throws ResourceNotFoundException {

		Customer customer = this.customerRepo.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", customerId.toString()));

		List<Booking> bookings = this.bookingRepo.findByCustomer(customer);

		List<BookingDto> bookingDtos = new ArrayList<BookingDto>();
		for (Booking b : bookings) {
			List<TicketOutputDto> ticketDtos = b.getTickets().stream()
					.map((t) -> this.modelMapper.map(t, TicketOutputDto.class)).collect(Collectors.toList());

			BookingDto bookingDto = this.bookingToDto(b);

			bookingDto.setTicketDtos(ticketDtos);

			bookingDtos.add(bookingDto);
		}

		return bookingDtos;
	}

	@Override
	public BookingDto cancelBooking(Integer bookingId, Integer customerId) throws ResourceNotFoundException {

		Booking booking = this.bookingRepo.findById(bookingId)
				.orElseThrow(() -> new ResourceNotFoundException("Booking", "Id", bookingId.toString()));

		if (booking.getCustomer().getCustomerId() == customerId) {

			booking.setBookingStatus(BookingStatus.CANCELLED);
			List<Ticket> tickets = booking.getTickets();

			for (Ticket t : tickets) {
				if (t.getDailyActivity().getActivityDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
						.isAfter(LocalDateTime.now().plusDays(1))) {

					this.ticketService.cancelTicket(customerId, t.getTicketId());
				}

			}
			booking = this.bookingRepo.save(booking);
		} else {
			throw new UnauthorisedException("You are only allowed to cancel your Bookings!");
		}

		return this.bookingToDto(booking);
	}

	private BookingDto bookingToDto(Booking booking) {
		BookingDto bookingDTO = this.modelMapper.map(booking, BookingDto.class);
		List<TicketOutputDto> ticketDTOs = bookingDTO.getTicketDtos().stream()
				.map((t) -> this.modelMapper.map(t, TicketOutputDto.class)).collect(Collectors.toList());
		bookingDTO.setTicketDtos(ticketDTOs);

		return bookingDTO;
	}

}
