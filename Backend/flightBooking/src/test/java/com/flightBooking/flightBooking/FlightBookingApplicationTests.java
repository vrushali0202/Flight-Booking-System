package com.flightBooking.flightBooking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.flightBooking.entity.BookingData;
import com.flightBooking.entity.FlightData;
import com.flightBooking.exception.BookingDataException;
import com.flightBooking.repository.BookingDataRepo;
import com.flightBooking.service.BookingDataServiceImp;
import com.flightBooking.service.FlightClient;

@SpringBootTest
class FlightBookingApplicationTests {

	@Test
	void contextLoads() {
	}

	@Mock
	private BookingDataRepo repo;

	@Mock
	private FlightClient fClient;

	@InjectMocks
	private BookingDataServiceImp bookingDataService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetBookingData() { 
		// Mock data
		BookingData bookingData = new BookingData();
		when(repo.findAll()).thenReturn(List.of(bookingData));
		// Mock flight client response
		FlightData flightData = new FlightData();
		when(fClient.getByFlightId(anyLong())).thenReturn(flightData);
		// Perform the test
		assertEquals(1, bookingDataService.getBookingData().size());
		// Verify interactions
		verify(repo, times(1)).findAll();
		verify(fClient, times(1)).getByFlightId(anyLong());
	}

	@Test
	void testGetByBookingId_BookingIdPresent() {
		long bookingId = 1L;
		BookingData bookingData = new BookingData();
		when(repo.findByBookingId(bookingId)).thenReturn(bookingData);
		FlightData flightData = new FlightData();
		when(fClient.getByFlightId(anyLong())).thenReturn(flightData);
		BookingData result = bookingDataService.getByBookingId(bookingId);
		assertNotNull(result);
		verify(repo, times(1)).findByBookingId(bookingId);
		verify(fClient, times(1)).getByFlightId(anyLong());
	}

	@Test
	void testGetByBookingId_BookingIdNotPresent() {
		long nonExistentBookingId = 999L;
		when(repo.findByBookingId(nonExistentBookingId)).thenReturn(null);
		assertThrows(BookingDataException.class, () -> bookingDataService.getByBookingId(nonExistentBookingId));
		verify(repo, times(1)).findByBookingId(nonExistentBookingId);
		verifyNoInteractions(fClient);
	}
}
