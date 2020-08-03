package com.testapp.busbooking.rest.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeatReservationLineResponse {

	private String number;
	private BigDecimal price;
}
