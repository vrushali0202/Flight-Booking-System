package com.flightBooking.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightBooking.entity.BookingData;
import com.flightBooking.entity.FlightData;
import com.flightBooking.entity.PassengerData;
import com.flightBooking.entity.UserData;
import com.flightBooking.service.BookingDataService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/booking")
public class BookingDataController {

	@Autowired
	BookingDataService service;

	@GetMapping()
	List<BookingData> getBookingData() {
		return service.getBookingData();
	}

	@GetMapping("/{bookingId}")
	BookingData getByBookingId(@PathVariable("bookingId") long bookingId) {
		return service.getByBookingId(bookingId);
	}

	@GetMapping("/{bookingId}/flightData")
	FlightData getByFlightId(@PathVariable("bookingId") long flightId) {
		return service.getByFlightId(flightId);
	}

	@GetMapping("/userData/{username}")
	List<BookingData> getByUsername(@PathVariable("username") String username) {
		return service.getByUsername(username);
	}

	@PostMapping("/bookedSeat")
	BookingData addBookingData(@RequestBody BookingData bookingData) {
		 return service.addBookingData(bookingData);
	}
	
	@PutMapping("/updateData/{bookingId}/{passesngerIndex}")
	BookingData updateBookingData(@PathVariable("bookingId") long bookingId,@PathVariable("passesngerIndex")int passesngerIndex,@RequestBody PassengerData bookingData) {
		return service.updateBookingData(bookingId,passesngerIndex,bookingData);
	}
   
	@DeleteMapping("/cancelSeat/{bookingId}/{seatIndex}")
	void cancelSeats(@PathVariable("bookingId") long bookingId, @PathVariable("seatIndex") int seatIndex) {
		 service.cancelSeats(bookingId, seatIndex);
	}

	
}
