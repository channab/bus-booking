package com.testapp.busbooking.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatReservationLine {

	@Id
	@GeneratedValue
	private long id;
    @ManyToOne
    @JoinColumn(name="seat_id")
	private Seat seat;
	private BigDecimal price;
    @ManyToOne
    @JoinColumn(name="reservation_id")
	private SeatsReservation seatsReservation;
}
