package com.flight.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight.entity.FlightData;
import com.flight.exception.FlightDataException;
import com.flight.repository.FlightDataRepo;

@Service
public class FlightDataServiceImp implements FlightDataService {
	@Autowired
	FlightDataRepo repo;

	@Override
	public List<FlightData> getAllFlights() {
		return repo.findAll();
	}

	@Override
	public FlightData getByFlightId(long flightId) {
		return repo.findByFlightId(flightId);
	}

	@Override
	public List<FlightData> getByData(String source, String destination) {
		return repo.findByData(source.toLowerCase(), destination.toLowerCase());
	}

	@Override
	public FlightData addFlight(FlightData flight) {
		flight.setSource(flight.getSource().toLowerCase());
		flight.setDestination(flight.getDestination().toLowerCase());
		return repo.save(flight);
	}

	
	@Override
	public FlightData updateFlight(long flightId, FlightData flight) {
	    FlightData flightData = repo.findByFlightId(flightId);
	        flightData.setFlightName(flight.getFlightName());
	        flightData.setArrivalTime(flight.getArrivalTime());
	        flightData.setDepartureTime(flight.getDepartureTime());
	        flightData.setSource(flight.getSource().toLowerCase());
	        flightData.setDestination(flight.getDestination().toLowerCase());
	        flightData.setAvailableSeats(flight.getAvailableSeats());
	        flightData.setPrice(flight.getPrice());
	        return repo.save(flightData);
	        

	}


	@Override
	public String deleteFlight(long id) {
		repo.deleteById((int) id);
		return "Flight has been deleted";
	}
}
