package com.flight.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flight.entity.FlightData;

@Repository
public interface FlightDataRepo extends MongoRepository<FlightData,Integer> {
	@Query("{'source' : :#{#source}, 'destination' : :#{#destination}}")
	List<FlightData> findByData(@Param("source") String source, @Param("destination") String destination);
	
	@Query("{'flightId':?0}")
	FlightData findByFlightId(@Param ("flightId")long flightId );
	
}
