package com.testapp.busbooking.service;

import java.time.LocalDateTime;

import com.testapp.busbooking.rest.model.BusScheduleResponse;
import com.testapp.busbooking.rest.model.BusesSearchResponse;
import com.testapp.busbooking.rest.model.SelectSeatRequest;

public interface BusScheduleService {

	BusesSearchResponse searchBuses(Long sourceCityId, Long destinationCityId, LocalDateTime startDateTime,
			LocalDateTime returntDateTime);
	
	BusScheduleResponse getBusSchedule(Long busScheduleId);
}
