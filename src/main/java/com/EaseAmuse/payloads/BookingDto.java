package com.EaseAmuse.payloads;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.EaseAmuse.models.BookingStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookingDto {

	private Integer bookingId;
	private Double totalPrice;
	private LocalDateTime bookingDateTime;
	private BookingStatus bookingStatus;
	private Integer customerId;
	private String customerName;
	private List<TicketOutputDto> ticketDtos = new ArrayList<>();

}
