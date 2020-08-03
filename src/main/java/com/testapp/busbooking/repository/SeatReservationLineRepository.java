package com.testapp.busbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.testapp.busbooking.entity.Seat;
import com.testapp.busbooking.entity.SeatReservationLine;
import com.testapp.busbooking.entity.SeatsReservation;

public interface SeatReservationLineRepository extends JpaRepository<SeatReservationLine, Long> {

	@Query("SELECT sl FROM SeatReservationLine sl WHERE sl.seatsReservation = :seatsReservation AND sl.seat = :seat")
	SeatReservationLine getSeatReservationLine(@Param("seatsReservation") SeatsReservation seatsReservation,
			@Param("seat") Seat seat);
}
