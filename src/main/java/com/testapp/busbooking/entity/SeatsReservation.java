package com.testapp.busbooking.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;

import com.testapp.busbooking.enums.BookingStatus;
import com.testapp.busbooking.enums.ReservationStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatsReservation {

	@Id
	@GeneratedValue
	private long id;
    @ManyToOne
    @JoinColumn(name="customer_id")
	private Customer customer;
    @Builder.Default
	private BigDecimal totalPrice = BigDecimal.ZERO;
    @ManyToOne
    @JoinColumn(name="bus_schedule_id")
	private BusSchedule busSchedule;
	@OneToMany(mappedBy = "seatsReservation", cascade = CascadeType.PERSIST)
	private List<SeatReservationLine> seatReservationLines;
	@Enumerated(EnumType.STRING)
	private BookingStatus bookingStatus;
}
