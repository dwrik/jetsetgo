package com.dwrik.checkin.service;

import com.dwrik.checkin.exception.UnableToCheckinException;
import com.dwrik.checkin.model.Checkin;
import com.dwrik.checkin.model.PendingCheckin;
import com.dwrik.checkin.repository.CheckinRepository;
import com.dwrik.checkin.repository.PendingCheckinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class CheckinServiceImpl implements CheckinService {

	@Autowired
	private StreamBridge streamBridge;

	@Autowired
	private CheckinRepository checkinRepository;

	@Autowired
	private PendingCheckinRepository pendingCheckinRepository;

	@Override
	@Transactional
	public Checkin createNewCheckin(Long bookingId, Long userId) {
		PendingCheckin pendingCheckin = pendingCheckinRepository.findByBookingIdAndUserId(bookingId, userId)
				.orElseThrow(() -> new UnableToCheckinException("booking not found or already checked in"));

		int seatNumber = generateSeatNumber(pendingCheckin);

		Checkin checkin = new Checkin();
		checkin.setSeatNumber(seatNumber);
		checkin.setFlightId(pendingCheckin.getFlightId());
		checkin.setBookingId(pendingCheckin.getBookingId());
		checkin.setCheckinStatus(Boolean.TRUE);

		Checkin saved = checkinRepository.save(checkin);
		pendingCheckinRepository.deleteByBookingId(bookingId);

		streamBridge.send("checkin-update", Map.of(
				"bookingId", bookingId,
				"userId", userId,
				"seatNumber", seatNumber
		));

		return saved;
	}

	@Override
	@Transactional
	public void createNewPendingCheckin(PendingCheckin pendingCheckin) {
		pendingCheckinRepository.save(pendingCheckin);
	}

	@Override
	@Transactional
	public void deleteExistingCheckinOrPendingChecking(Long bookingId) {
		checkinRepository.deleteByBookingId(bookingId);
		pendingCheckinRepository.deleteByBookingId(bookingId);
	}

	private int generateSeatNumber(PendingCheckin pendingCheckin) {
		Iterable<Checkin> checkins = checkinRepository.findByFlightId(pendingCheckin.getFlightId());
		Set<Integer> allocatedSeatNumbers = new HashSet<>();

		checkins.forEach(checkin -> allocatedSeatNumbers.add(checkin.getSeatNumber()));

		for (int seatNumber = 1; seatNumber <= pendingCheckin.getTotalSeats(); seatNumber++) {
			if (!allocatedSeatNumbers.contains(seatNumber)) {
				return seatNumber;
			}
		}

		return 0;
	}
}
