package com.flightBooking.service;

import java.util.List;

import com.flightBooking.entity.BookingData;
import com.flightBooking.entity.FlightData;
import com.flightBooking.entity.PassengerData;
import com.flightBooking.entity.UserData;

public interface BookingDataService {
	List<BookingData> getBookingData();

	BookingData getByBookingId(long bookingId);

	FlightData getByFlightId(long flightId);

	List<BookingData> getByUsername(String username);

	BookingData addBookingData(BookingData bookingData);

	BookingData updateBookingData(long bookingId,int passesngerIndex,PassengerData bookingData);

	String deleteBookingData(long bookingId);
     
	void cancelSeats(long bookingId, int seatIndex);
}
