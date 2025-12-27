package com.citizen.portal.controller;

import org.springframework.web.bind.annotation.*;
import com.citizen.portal.model.Booking;
import com.citizen.portal.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @PostMapping("/accept")
    public Booking accept(@RequestBody Booking booking) {
        return service.acceptBooking(booking);
    }
}
