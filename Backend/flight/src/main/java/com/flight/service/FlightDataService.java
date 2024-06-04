package com.flight.service;

import java.util.List;

import com.flight.entity.FlightData;

public interface FlightDataService {
	List<FlightData> getAllFlights();

	FlightData getByFlightId(long flightId);
	
	List<FlightData> getByData(String source, String Destination);

	FlightData addFlight(FlightData flight); 
	
	FlightData updateFlight(long id,FlightData flight);

	String deleteFlight(long id);
}

