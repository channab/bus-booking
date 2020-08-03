package com.testapp.busbooking.rest;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.testapp.busbooking.rest.model.BusScheduleResponse;
import com.testapp.busbooking.rest.model.BusesSearchResponse;
import com.testapp.busbooking.rest.model.SeatsReservationConfirmationRequest;
import com.testapp.busbooking.rest.model.SeatsReservationResponse;
import com.testapp.busbooking.rest.model.SelectSeatRequest;
import com.testapp.busbooking.service.BusScheduleService;
import com.testapp.busbooking.service.SeatsReservationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class ReservationController {

	private BusScheduleService busScheduleService;
	
	private SeatsReservationService seatsReservationService;
	
	@GetMapping("search-buses")
	public BusesSearchResponse searchBuses(@RequestParam(value = "sourceCityId") Long sourceCityId,
			@RequestParam(value = "destinationCityId") Long destinationCityId,
			@RequestParam(value = "startDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
			@RequestParam(value = "returnDateTime", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime returntDateTime) {

		return busScheduleService.searchBuses(sourceCityId, destinationCityId, startDateTime, returntDateTime);
	}
	
	@GetMapping("bus-schedules/{busScheduleId}")
	public BusScheduleResponse searchBuses(@PathVariable("busScheduleId") Long busScheduleId) {

		return busScheduleService.getBusSchedule(busScheduleId);
	}
	
	
	@PostMapping("seats/select")
	public void selectSeat(@RequestBody SelectSeatRequest selectSeatRequest) {

		seatsReservationService.updateSeatReservation(selectSeatRequest);
	}
	
	@GetMapping("seats-reservations/{seatsReservationId}")
	public SeatsReservationResponse getSeatsReservation(@PathVariable Long seatsReservationId) {

		return seatsReservationService.getSeatsReservation(seatsReservationId);
	}
	
	@PostMapping("seats-reservations/confirm")
	public void confirmReservation(@RequestBody SeatsReservationConfirmationRequest seatsReservationConfirmationRequest ) {

		seatsReservationService.confirmReservation(seatsReservationConfirmationRequest);
	}
}
