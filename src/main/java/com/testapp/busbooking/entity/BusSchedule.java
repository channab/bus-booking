package com.testapp.busbooking.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.testapp.busbooking.enums.ScheduleStatus;

import lombok.Data;

@Entity
@Data
public class BusSchedule {

	@Id
	@GeneratedValue
	private long id;
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime startDateTime;
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime returnDateTime;
    @ManyToOne
    @JoinColumn(name="source_city_id")
	private City sourceCity;
    @ManyToOne
    @JoinColumn(name="destination_city_id")
	private City destinationCity;
    @ManyToOne
    @JoinColumn(name="bus_id")
    private Bus bus;
    @Enumerated(EnumType.STRING)
    private ScheduleStatus scheduleStatus;
    private BigDecimal price;
}
