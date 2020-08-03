package com.testapp.busbooking.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.testapp.busbooking.entity.BusSchedule;
import com.testapp.busbooking.entity.City;
import com.testapp.busbooking.enums.ScheduleStatus;

public interface BusScheduleRepository extends JpaRepository<BusSchedule, Long>{

	
	@Query("SELECT bs FROM BusSchedule bs WHERE bs.sourceCity = :sourceCity AND bs.destinationCity = :destinationCity "
			+ "AND bs.startDateTime > :startDateTime AND bs.scheduleStatus = :scheduleStatus ORDER BY bs.price ASC")
	List<BusSchedule> searchBuses(@Param("sourceCity") City sourceCity, @Param("destinationCity") City destinationCity,

			@Param("startDateTime") LocalDateTime startDateTime,

			@Param("scheduleStatus") ScheduleStatus scheduleStatus);

	@Query("SELECT bs FROM BusSchedule bs WHERE bs.sourceCity = :sourceCity AND bs.destinationCity = :destinationCity "
			+ "AND bs.startDateTime > :startDateTime AND bs.returnDateTime < :returnDateTime AND bs.scheduleStatus = :scheduleStatus ORDER BY bs.price ASC")
	List<BusSchedule> searchBuses(@Param("sourceCity") City sourceCity, @Param("destinationCity") City destinationCity,

			@Param("startDateTime") LocalDateTime startDateTime,

			@Param("returnDateTime") LocalDateTime returnDateTime,

			@Param("scheduleStatus") ScheduleStatus scheduleStatus);
	 
	
	
	/*
	 * @Query("SELECT bs FROM BusSchedule bs WHERE bs.sourceCity = :sourceCity AND bs.destinationCity = :destinationCity "
	 * + "AND bs.startDateTime > :startDateTime") List<BusSchedule>
	 * searchBuses(@Param("sourceCity") City sourceCity, @Param("destinationCity")
	 * City destinationCity,
	 * 
	 * @Param("startDateTime") LocalDateTime startDateTime);
	 * 
	 * @Query("SELECT bs FROM BusSchedule bs WHERE bs.sourceCity = :sourceCity AND bs.destinationCity = :destinationCity "
	 * +
	 * "AND bs.startDateTime > :startDateTime AND bs.returnDateTime < :returnDateTime"
	 * ) List<BusSchedule> searchBuses(@Param("sourceCity") City
	 * sourceCity, @Param("destinationCity") City destinationCity,
	 * 
	 * @Param("startDateTime") LocalDateTime startDateTime,
	 * 
	 * @Param("returnDateTime") LocalDateTime returnDateTime);
	 */
}
