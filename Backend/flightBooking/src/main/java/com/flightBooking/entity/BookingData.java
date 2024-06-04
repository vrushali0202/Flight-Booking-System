package com.flightBooking.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "BookingData")
public class BookingData {

	@Id
	private long bookingId;
	private String username;
	private long flightId;
	private FlightData flightData;
	private String bookingDate;
	private String travelDate;
	private int bookedSeats;
	private List<PassengerData> passengersData;

	public BookingData() {
	};

	
	public BookingData(long bookingId, String username, long flightId, FlightData flightData, String bookingDate,
			String travelDate, int bookedSeats, List<PassengerData> passengersData) {
		super();
		this.bookingId = bookingId;
		this.username = username;
		this.flightId = flightId;
		this.flightData = flightData;
		this.bookingDate = bookingDate;
		this.travelDate = travelDate;
		this.bookedSeats = bookedSeats;
		this.passengersData = passengersData;
	}


	public FlightData getFlightData() {
		return flightData;
	}

	public void setFlightData(FlightData flightData) {
		this.flightData = flightData;
	}

	public long getBookingId() {
		return bookingId;
	}

	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}

	public long getFlightId() {
		return flightId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}

	public List<PassengerData> getPassengersData() {
		return passengersData;
	}

	public void setPassengersData(List<PassengerData> passengersData) {
		this.passengersData = passengersData;
	}

	public void setFlightId(long flightId) {
		this.flightId = flightId;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}


	public int getBookedSeats() {
		return bookedSeats;
	}

	public void setBookedSeats(int bookedSeats) {
		this.bookedSeats = bookedSeats;
	}

}
