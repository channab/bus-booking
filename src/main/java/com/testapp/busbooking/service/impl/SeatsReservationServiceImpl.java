package com.testapp.busbooking.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.testapp.busbooking.entity.BusSchedule;
import com.testapp.busbooking.entity.Customer;
import com.testapp.busbooking.entity.Seat;
import com.testapp.busbooking.entity.SeatReservationLine;
import com.testapp.busbooking.entity.SeatsReservation;
import com.testapp.busbooking.enums.BookingStatus;
import com.testapp.busbooking.enums.ReservationStatus;
import com.testapp.busbooking.exceptions.BadRequestExpection;
import com.testapp.busbooking.repository.BusScheduleRepository;
import com.testapp.busbooking.repository.CustomerRepository;
import com.testapp.busbooking.repository.SeatRepository;
import com.testapp.busbooking.repository.SeatReservationLineRepository;
import com.testapp.busbooking.repository.SeatsReservationRepository;
import com.testapp.busbooking.rest.model.BusSearchResponse;
import com.testapp.busbooking.rest.model.SeatReservationLineResponse;
import com.testapp.busbooking.rest.model.SeatsReservationConfirmationRequest;
import com.testapp.busbooking.rest.model.SeatsReservationResponse;
import com.testapp.busbooking.rest.model.SelectSeatRequest;
import com.testapp.busbooking.service.SeatsReservationService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatsReservationServiceImpl implements SeatsReservationService {

	private final BusScheduleRepository busScheduleRepository;
	
	private final SeatsReservationRepository seatsReservationRepository;
	
	private final CustomerRepository customerRepository;
	
	private final SeatRepository seatRepository;
	
	private final SeatReservationLineRepository seatReservationLineRepository;
	
    @Value("${app.ticket.price.levy}")
    private BigDecimal ticketLevy;
	
	@Override
	@Transactional
	public void updateSeatReservation(SelectSeatRequest selectSeatRequest) {
		
		BusSchedule busSchedule = busScheduleRepository.getOne(selectSeatRequest.getBusScheduleId());
		
		if(busSchedule == null) {
			throw new EntityNotFoundException("Bus Schedule not found in the system");
		}
		
		Customer customer = customerRepository.getOne(selectSeatRequest.getCustomerId());
		
		Seat seat = seatRepository.getOne(selectSeatRequest.getSeatId());
		
		if(seat == null) {
			throw new EntityNotFoundException("Seat not found in the system");
		}
		
		if(!seat.getReservationStatus().equals(ReservationStatus.OPEN)) {
			throw new BadRequestExpection(String.format("Seat %s is on hold or already booked", seat.getNumber()));
		}
		
		SeatsReservation seatsReservation = seatsReservationRepository.getReservation(busSchedule, customer, BookingStatus.OPEN);
		
		SeatReservationLine seatReservationLine = null;
		
		if(seatsReservation == null) {
			seatsReservation = SeatsReservation.builder().busSchedule(busSchedule).customer(customer)
					.bookingStatus(BookingStatus.OPEN).build();
			
			List<SeatReservationLine> seatReservationLines = new ArrayList<>();
			
			seatReservationLine = SeatReservationLine.builder().seat(seat).seatsReservation(seatsReservation).
					price(busSchedule.getPrice().multiply(ticketLevy)).build();
			
			seatReservationLines.add(seatReservationLine);
			
			seatsReservation.setSeatReservationLines(seatReservationLines);
		}
		else {
			seatReservationLine = seatReservationLineRepository.getSeatReservationLine(seatsReservation, seat);
			
			if(seatReservationLine != null) {
				throw new BadRequestExpection(String.format("You have already reserved the seat %s", seat.getNumber()));
			}
		}
		
		seatsReservation.setTotalPrice(seatsReservation.getTotalPrice().add(seatReservationLine.getPrice()));
		
		seatsReservation = seatsReservationRepository.save(seatsReservation);
		
		seat.setReservationStatus(ReservationStatus.ONHOLD);
		seat.setLastReservedTime(LocalDateTime.now());
		
	}

	@Override
	public SeatsReservationResponse getSeatsReservation(Long id) {
		
		SeatsReservation seatsReservation = seatsReservationRepository.getOne(id);
		
		SeatsReservationResponse seatsReservationResponse = SeatsReservationResponse.builder().id(seatsReservation.getId()).busNumber(seatsReservation.getBusSchedule().getBus().getNumber())
				.customerName(seatsReservation.getCustomer().getName()).totalPrice(seatsReservation.getTotalPrice()).bookingStatus(seatsReservation.getBookingStatus()).departureCity(seatsReservation.getBusSchedule().getSourceCity().getName())
				.arrivalCity(seatsReservation.getBusSchedule().getDestinationCity().getName()).departureTime(seatsReservation.getBusSchedule().getStartDateTime()).build();
		
		List<SeatReservationLineResponse> seatReservationLineResponses = seatsReservation.getSeatReservationLines().stream().map(line -> {
			
			return SeatReservationLineResponse.builder().number(line.getSeat().getNumber()).price(line.getPrice()).build();
			
		}).collect(Collectors.toList());
		
		seatsReservationResponse.setSeatReservationLineResponses(seatReservationLineResponses);
		
		return seatsReservationResponse;
	}

	@Override
	@Transactional
	public void confirmReservation(SeatsReservationConfirmationRequest seatsReservationConfirmationRequest) {
		SeatsReservation seatsReservation = seatsReservationRepository.getOne(seatsReservationConfirmationRequest.getSeatsReservationId());
		
		if(seatsReservation == null) {
			throw new EntityNotFoundException("Seats reservation not found in the system");
		}
		
		seatsReservation.setBookingStatus(BookingStatus.CONFIRMED);
		
		seatsReservation.getSeatReservationLines().forEach(line -> {
			Seat seat = line.getSeat();
			seat.setReservationStatus(ReservationStatus.CONFIRMED);
			seat.setLastReservedTime(null);
		});
	}

}
