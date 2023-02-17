package com.dwrik.checkin.service;

import com.dwrik.checkin.model.Checkin;
import com.dwrik.checkin.model.PendingCheckin;

public interface CheckinService {

	Checkin createNewCheckin(Long bookingId, Long userId);

	void createNewPendingCheckin(PendingCheckin pendingCheckin);

	void deleteExistingCheckinOrPendingChecking(Long bookingId);
}
