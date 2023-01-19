package com.EaseAmuse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.EaseAmuse.models.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer> {

}
