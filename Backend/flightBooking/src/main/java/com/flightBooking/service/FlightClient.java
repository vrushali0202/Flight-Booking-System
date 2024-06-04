package com.flightBooking.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.flightBooking.entity.FlightData;

@FeignClient(url = "http://localhost:8081", value = "Flight-Client")
public interface FlightClient {
	@GetMapping("/flight/{id}")
	public FlightData getByFlightId(@PathVariable("id") long id);
	
	@PutMapping("flight/updateFlight/{id}")
	public FlightData updateFlight(@PathVariable("id") long id, @RequestBody FlightData flight) ;
}
