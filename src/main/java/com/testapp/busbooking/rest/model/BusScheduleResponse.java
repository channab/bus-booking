package com.testapp.busbooking.rest.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusScheduleResponse {

	private Long id;
	private String busNumber;
	private String operatorName;
	private String departureCity;
	private String arrivalCity;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	private BigDecimal price;
	private Set<SeatResponse> seatResponses;
}
