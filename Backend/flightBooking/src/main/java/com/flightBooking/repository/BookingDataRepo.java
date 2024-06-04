package com.flightBooking.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.flightBooking.entity.BookingData;
import com.flightBooking.entity.FlightData;
import com.flightBooking.entity.UserData;

@Repository
public interface BookingDataRepo extends MongoRepository<BookingData,Long>{
	@Query("{'bookingId':?0}")
	BookingData findByBookingId(@Param ("bookingId")long bookingId );
	
	//@Query("{'username' : :#{#username}")
	List<BookingData> findByUsername(String username );
	
}
