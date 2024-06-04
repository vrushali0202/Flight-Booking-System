package com.flightBooking.entity;


public class FlightData {

	private long flightId;
	private String flightName;
	private String arrivalTime;
	private String departureTime;
	private String source;
	private String destination;
	private int availableSeats;
	private double price;
	
	

	public FlightData() {
		super();
	}

	
	public FlightData(long flightId, String flightName, String arrivalTime, String departureTime, String source,
			String destination, int availableSeats, double price) {
		super();
		this.flightId = flightId;
		this.flightName = flightName;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.source = source;
		this.destination = destination;
		this.availableSeats = availableSeats;
		this.price = price;
	}


	public long getFlightId() {
		return flightId;
	}

	public void setFlightId(long flightId) {
		this.flightId = flightId;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}

	
}
