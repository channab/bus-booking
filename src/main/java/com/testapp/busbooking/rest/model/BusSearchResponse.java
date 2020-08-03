package com.testapp.busbooking.rest.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusSearchResponse {

	private Long id;
	private String busNumber;
	private String operatorName;
	private String departureCity;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	private BigDecimal price;
}
