package com.testapp.busbooking.service;

import com.testapp.busbooking.rest.model.SeatsReservationConfirmationRequest;
import com.testapp.busbooking.rest.model.SeatsReservationResponse;
import com.testapp.busbooking.rest.model.SelectSeatRequest;

public interface SeatsReservationService {

	void updateSeatReservation(SelectSeatRequest selectSeatRequest);
	
	SeatsReservationResponse getSeatsReservation(Long id);
	
	void confirmReservation(SeatsReservationConfirmationRequest seatsReservationConfirmationRequest);
}
