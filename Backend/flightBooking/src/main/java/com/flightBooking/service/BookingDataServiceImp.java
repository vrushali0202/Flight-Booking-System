package com.flightBooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightBooking.entity.BookingData;
import com.flightBooking.entity.FlightData;
import com.flightBooking.entity.PassengerData;
import com.flightBooking.entity.UserData;
import com.flightBooking.exception.BookingDataException;
import com.flightBooking.repository.BookingDataRepo;

@Service
public class BookingDataServiceImp implements BookingDataService {
	@Autowired
	BookingDataRepo repo;

	@Autowired
	FlightClient fClient;

	@Override
	public List<BookingData> getBookingData() {
		List<BookingData> bookingData = repo.findAll();
		for (BookingData data : bookingData) {
			FlightData flight = fClient.getByFlightId(data.getFlightId());
			data.setFlightData(flight);
		}
		return bookingData;
	}

	@Override
	public BookingData getByBookingId(long bookingId) {
		BookingData data = repo.findByBookingId(bookingId);
		if (data != null) {
			FlightData flight = fClient.getByFlightId(data.getFlightId());
			data.setFlightData(flight);
			return data;
		} else {
			throw new BookingDataException("booking id is not present");
		}

	}

	@Override
	public FlightData getByFlightId(long bookingId) {
		BookingData bookingData = repo.findByBookingId(bookingId);
		if (bookingData != null) {
			return fClient.getByFlightId(bookingData.getFlightId());
		} else {
			throw new BookingDataException("booking id is not present");
		}
	}

	@Override
	public List<BookingData> getByUsername(String username) {
		List<BookingData> bookingData = repo.findByUsername(username);
		for (BookingData data : bookingData) {
			FlightData flight = fClient.getByFlightId(data.getFlightId());
			data.setFlightData(flight);
		}
		return bookingData;
	}


	@Override
	public BookingData addBookingData(BookingData bookingData) {
	    // generate random booking id
	    long randomBookingId = (long) (Math.random() * Long.MAX_VALUE/1000);
	    bookingData.setBookingId(randomBookingId);
       
	    BookingData data = repo.save(bookingData);

	    FlightData flight = fClient.getByFlightId(data.getFlightId());
	    int availableSeats = flight.getAvailableSeats() - bookingData.getBookedSeats();
	    flight.setAvailableSeats(availableSeats);
	    fClient.updateFlight(data.getFlightId(), flight);
        data.setFlightData(flight);
	   return data;
	}


	@Override
	public BookingData updateBookingData(long bookingId,int passesngerIndex, PassengerData pData ) {
		BookingData data = repo.findByBookingId(bookingId);
		PassengerData passesngerData= data.getPassengersData().get(passesngerIndex);
		passesngerData.setCheckInStatus(pData.isCheckInStatus());
	    return repo.save(data);
	}

	@Override
	public String deleteBookingData(long bookingId) {
		BookingData data = repo.findByBookingId(bookingId);
		repo.deleteById( bookingId);
		return "booking data has been deleted";
	}

	@Override
	public void cancelSeats(long bookingId, int seatIndex) {
		BookingData data = repo.findByBookingId(bookingId);
		// update in flight data
		if (data.getBookedSeats() >= 1) {
			FlightData flight = fClient.getByFlightId(data.getFlightId());
			int availableSeats = flight.getAvailableSeats() + 1;
			flight.setAvailableSeats(availableSeats);
			fClient.updateFlight(data.getFlightId(), flight);
			data.setBookedSeats(data.getBookedSeats() - 1);
			data.getPassengersData().remove(seatIndex);
			if(data.getPassengersData().size()>0) {
				 repo.save(data);
			}else {
				repo.deleteById( bookingId);
			}
			
		}

		else {
			throw new BookingDataException("cancelled seats are more than booked seats");
		}

	}

}
