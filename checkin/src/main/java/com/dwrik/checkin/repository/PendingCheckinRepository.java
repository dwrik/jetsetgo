package com.dwrik.checkin.repository;

import com.dwrik.checkin.model.PendingCheckin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PendingCheckinRepository extends CrudRepository<PendingCheckin, Long> {

	Optional<PendingCheckin> findByBookingIdAndUserId(Long bookingId, Long userId);

	void deleteByBookingId(Long bookingId);
}
