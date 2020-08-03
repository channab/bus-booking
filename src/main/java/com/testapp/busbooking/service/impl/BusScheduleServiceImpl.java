package com.testapp.busbooking.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.testapp.busbooking.entity.BusSchedule;
import com.testapp.busbooking.entity.City;
import com.testapp.busbooking.entity.Seat;
import com.testapp.busbooking.enums.ScheduleStatus;
import com.testapp.busbooking.repository.BusScheduleRepository;
import com.testapp.busbooking.rest.model.BusScheduleResponse;
import com.testapp.busbooking.rest.model.BusSearchResponse;
import com.testapp.busbooking.rest.model.BusesSearchResponse;
import com.testapp.busbooking.rest.model.SeatResponse;
import com.testapp.busbooking.rest.model.SelectSeatRequest;
import com.testapp.busbooking.service.BusScheduleService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BusScheduleServiceImpl implements BusScheduleService {
	
	private BusScheduleRepository busScheduleRepository;
	
	@Override
	public BusesSearchResponse searchBuses(Long sourceCityId, Long destinationCityId, LocalDateTime startDateTime,
			LocalDateTime returnDateTime) {
		
		BusesSearchResponse busesSearchResponse = new BusesSearchResponse();
		
		List<BusSchedule> busSchedules = null;
		
		if(returnDateTime != null) {
			busSchedules = busScheduleRepository.searchBuses(new City(sourceCityId), new City(destinationCityId), startDateTime, returnDateTime, ScheduleStatus.OPEN);
		}
		else {
			busSchedules = busScheduleRepository.searchBuses(new City(sourceCityId), new City(destinationCityId), startDateTime, ScheduleStatus.OPEN);
		}
		
		List<BusSearchResponse> busSearchResponses = busSchedules.stream().map(busSchedule -> {
			
			return BusSearchResponse.builder().id(busSchedule.getId()).busNumber(busSchedule.getBus().getNumber())
			.operatorName(busSchedule.getBus().getOperator().getName()).departureCity(busSchedule.getDestinationCity().getName())
			.departureTime(busSchedule.getStartDateTime()).arrivalTime(busSchedule.getReturnDateTime()).price(busSchedule.getPrice()).build(); 
			
		}).collect(Collectors.toList());
		
		busesSearchResponse.setBusSearchResponses(busSearchResponses);
		busesSearchResponse.setCount(busSearchResponses.size());
		
		return busesSearchResponse;
	}

	@Override
	public BusScheduleResponse getBusSchedule(Long busScheduleId) {
		
		BusSchedule busSchedule = busScheduleRepository.getOne(busScheduleId);
		
		Set<SeatResponse> seatResponses = busSchedule.getBus().getSeats().stream().map(seat -> {
			
			return SeatResponse.builder().id(seat.getId()).number(seat.getNumber()).reservationStatus(seat.getReservationStatus()).build(); 
			
		}).collect(Collectors.toSet());
		
		BusScheduleResponse busScheduleResponse = BusScheduleResponse.builder().id(busSchedule.getId())
				.busNumber(busSchedule.getBus().getNumber()).operatorName(busSchedule.getBus().getOperator().getName())
				.departureCity(busSchedule.getSourceCity().getName())
				.arrivalCity(busSchedule.getDestinationCity().getName()).departureTime(busSchedule.getStartDateTime())
				.arrivalTime(busSchedule.getReturnDateTime()).price(busSchedule.getPrice()).build();
		
		busScheduleResponse.setSeatResponses(seatResponses);
		
		return busScheduleResponse;
	}

}
