package com.testapp.busbooking.rest.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.testapp.busbooking.enums.ReservationStatus;
import com.testapp.busbooking.rest.model.BusSearchResponse.BusSearchResponseBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatResponse {

	private long id;
	private String number;
	private ReservationStatus reservationStatus;
}
