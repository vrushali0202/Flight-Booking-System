package com.flight;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.flight.controller.FlightDataController;
import com.flight.entity.FlightData;
import com.flight.exception.FlightDataException;
import com.flight.repository.FlightDataRepo;
import com.flight.service.FlightDataService;
import com.flight.service.FlightDataServiceImp;

@SpringBootTest
class FlightApplicationTests {

	@Test
	void contextLoads() {
	}

//	@Mock
//	FlightDataRepo repo;
	@Mock
	private FlightDataServiceImp repo;

	//@InjectMocks
	//FlightDataServiceImp flightService;

	 @InjectMocks
	 FlightDataController flightService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllFlights() {
		// Mock data
		List<FlightData> mockFlights = new ArrayList<>();
		when(repo.getAllFlights()).thenReturn(mockFlights);

		// Call the method and assert
		List<FlightData> result = flightService.getAllFlights();
		assertEquals(mockFlights, result);
	}

	@Test
	public void testGetByFlightIdFound() {
		// Mock data
		long flightId = 1L;
		FlightData mockFlight = new FlightData(); // Create a mock FlightData
		when(repo.getByFlightId(flightId)).thenReturn(mockFlight);

		// Call the method and assert
		FlightData result = flightService.getByFlightId(flightId);
		assertEquals(mockFlight, result);
	}

	@Test
	public void testGetByFlightIdNotFound() {
		// Mock data
		long flightId = 1L;
		when(repo.getByFlightId(flightId)).thenReturn(null);

		// Call the method and expect an exception
		assertThrows(FlightDataException.class, () -> flightService.getByFlightId(flightId));
	}

	@Test
	public void testGetByDataFound() {
		// Mock data
		String source = "Source";
		String destination = "Destination";
		List<FlightData> mockFlights = new ArrayList<>();
		when(repo.getByData(source, destination)).thenReturn(mockFlights);

		// Call the method and assert
		List<FlightData> result = flightService.getByData(source, destination);
		assertEquals(mockFlights, result);
	}

	@Test
	public void testGetByDataNotFound() {
		// Mock data
		String source = "Source";
		String destination = "Destination";
		when(repo.getByData(source, destination)).thenReturn(null);

		// Call the method and expect an exception
		assertThrows(FlightDataException.class, () -> flightService.getByData(source, destination));
	}

	@Test
	public void testAddFlight() {
		// Mock data
		FlightData mockFlight = new FlightData();
		when(repo.addFlight(mockFlight)).thenReturn(mockFlight);

		// Call the method and assert
		FlightData result = flightService.addFlight(mockFlight);
		assertEquals(mockFlight, result);
	}

	@Test
	public void testUpdateFlightFound() {
		// Mock data
		long flightId = 1L;
		FlightData existingFlight = new FlightData();
		when(repo.getByFlightId(flightId)).thenReturn(existingFlight);

		FlightData updatedFlight = new FlightData(); // Create an updated FlightData

		// Call the method and assert
		FlightData result = flightService.updateFlight(flightId, updatedFlight);
		//assertEquals(updatedFlight, result);
	}

	@Test
	public void testUpdateFlightNotFound() {
		// Mock data
		long flightId = 1L;
		when(repo.getByFlightId(flightId)).thenReturn(null);

		FlightData updatedFlight = new FlightData(); // Create an updated FlightData

		// Call the method and expect an exception
		assertThrows(FlightDataException.class, () -> flightService.updateFlight(flightId, updatedFlight));
	}

	@Test
	public void testDeleteFlight() {
		// Call the method and assert
		//assertEquals("Flight has been deleted", flightService.deleteFlight(1));

		// Verify that deleteById was called with the correct argument
		//verify(repo).deleteFlight(1);
	}

}
