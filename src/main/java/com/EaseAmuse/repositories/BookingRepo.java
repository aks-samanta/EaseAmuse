package com.EaseAmuse.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EaseAmuse.models.Booking;
import com.EaseAmuse.models.Ticket;

public interface BookingRepo extends JpaRepository<Booking, Integer> {

}
