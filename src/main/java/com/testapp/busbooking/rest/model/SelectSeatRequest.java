package com.testapp.busbooking.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectSeatRequest {

	private Long seatId;
	private Long customerId;
	private Long busScheduleId;
}
