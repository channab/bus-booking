package com.testapp.busbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testapp.busbooking.entity.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {

}
