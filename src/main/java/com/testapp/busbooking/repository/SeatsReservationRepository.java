package com.testapp.busbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.testapp.busbooking.entity.BusSchedule;
import com.testapp.busbooking.entity.Customer;
import com.testapp.busbooking.entity.SeatsReservation;
import com.testapp.busbooking.enums.BookingStatus;

public interface SeatsReservationRepository extends JpaRepository<SeatsReservation, Long>{

	@Query("SELECT sr FROM SeatsReservation sr WHERE sr.busSchedule = :busSchedule AND sr.customer = :customer "
			+ "AND sr.bookingStatus = :bookingStatus")
	SeatsReservation getReservation(@Param("busSchedule") BusSchedule busSchedule,
			@Param("customer") Customer customer, @Param("bookingStatus") BookingStatus bookingStatus);
}
