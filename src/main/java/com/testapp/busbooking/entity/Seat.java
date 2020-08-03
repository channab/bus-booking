package com.testapp.busbooking.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.testapp.busbooking.enums.ReservationStatus;

import lombok.Data;

@Entity
@Data
public class Seat {

	@Id
	@GeneratedValue
	private long id;
	@Column(length = 10)
	private String number;
    @ManyToOne
    @JoinColumn(name="bus_id")
    private Bus bus;
	@Enumerated(EnumType.STRING)
	private ReservationStatus reservationStatus;
	@Column(columnDefinition = "TIMESTAMP", nullable = true)
	private LocalDateTime lastReservedTime;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seat other = (Seat) obj;
		if (bus == null) {
			if (other.bus != null)
				return false;
		} else if (!bus.equals(other.bus))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bus == null) ? 0 : bus.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}
}
