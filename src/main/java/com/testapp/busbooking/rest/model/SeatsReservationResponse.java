package com.testapp.busbooking.rest.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.testapp.busbooking.enums.BookingStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeatsReservationResponse {

	private Long id;
	private String customerName;
	private BigDecimal totalPrice;
	private BookingStatus bookingStatus;
	private String busNumber;
	private String departureCity;
	private String arrivalCity;
	private LocalDateTime departureTime;
	private List<SeatReservationLineResponse> seatReservationLineResponses;
}
