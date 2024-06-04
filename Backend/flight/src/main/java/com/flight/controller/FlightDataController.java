package com.flight.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.entity.FlightData;
import com.flight.exception.FlightDataException;
import com.flight.repository.FlightDataRepo;
import com.flight.service.FlightDataService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/flight")
public class FlightDataController {

	@Autowired
	FlightDataService flightDataService;

	@GetMapping()
	public List<FlightData> getAllFlights() {
		return flightDataService.getAllFlights();
	}

	@GetMapping("/{id}")
	public FlightData getByFlightId(@PathVariable("id") long flightId) {
		if (flightDataService.getByFlightId(flightId) != null) {
			return flightDataService.getByFlightId(flightId);
		} else {
			throw new FlightDataException("flight id is not found");

		}
	}

	@GetMapping("/{source}/{destination}")
	public List<FlightData> getByData(@PathVariable("source") String source,
			@PathVariable("destination") String destination) {
		if ( flightDataService.getByData(source, destination) != null) {
			return  flightDataService.getByData(source, destination);
		} else {
			throw new FlightDataException("flight id is not found");
		}
		
	}

	@PostMapping("/addFlight")
	public FlightData addFlight(@RequestBody FlightData flight) {
		return flightDataService.addFlight(flight);
	}

	@PutMapping("/updateFlight/{id}")
	public FlightData updateFlight(@PathVariable("id") long id, @RequestBody FlightData flight) {
		if (flightDataService.getByFlightId(id) != null) {
			 return flightDataService.updateFlight(id, flight);
		} else {
			throw new FlightDataException("flight id is not found");

		}
	}

	@DeleteMapping("/deleteFlight/{id}")
	public String deleteFlight(@PathVariable("id") long id) {
		return flightDataService.deleteFlight(id);
	}

}
