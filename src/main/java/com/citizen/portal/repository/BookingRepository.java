package com.citizen.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.citizen.portal.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
