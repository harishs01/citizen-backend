package com.citizen.portal.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citizen.portal.model.Booking;
import com.citizen.portal.model.Issue;
import com.citizen.portal.repository.BookingRepository;
import com.citizen.portal.repository.IssueRepository;

@Service
public class BookingService {

    private final BookingRepository bookingRepo;
    private final IssueRepository issueRepo;

    public BookingService(BookingRepository bookingRepo,
                          IssueRepository issueRepo) {
        this.bookingRepo = bookingRepo;
        this.issueRepo = issueRepo;
    }

    @Transactional
    public Booking acceptBooking(Booking booking) {

        // 1️⃣ Save booking
        booking.setStatus("ACCEPTED");
        Booking savedBooking = bookingRepo.save(booking);

        // 2️⃣ Update issue status + assign worker
        Issue issue = issueRepo.findById(booking.getIssueId()).orElse(null);

        if (issue != null) {
            issue.setStatus("BOOKED");
            issue.setWorkerId(booking.getWorkerId());
            issueRepo.save(issue);
        }

        return savedBooking;
    }
}
