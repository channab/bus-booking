package com.testapp.busbooking.rest.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusesSearchResponse {

	private List<BusSearchResponse> busSearchResponses;
	private long count;
}
