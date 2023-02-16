package com.dwrik.booking.service;

import com.dwrik.booking.dto.BookingDto;
import com.dwrik.booking.model.Booking;

public interface BookingService {

	Booking getBookingByBookingIdAndUserId(Long bookingId, Long userId);

	Iterable<Booking> getAllBookings(Long userId);

	Booking createNewBooking(String bearerToken, Long userId, BookingDto bookingDto);

	void deleteBookingByBookingIdAndUserId(Long bookingId, Long userId);
}
