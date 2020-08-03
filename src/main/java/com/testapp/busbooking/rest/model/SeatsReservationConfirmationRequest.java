package com.testapp.busbooking.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatsReservationConfirmationRequest {

	private Long seatsReservationId;
	private Long customerId;
	private String paymentMethod;
}
