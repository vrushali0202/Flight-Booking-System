package com.flight.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FlightDataException  extends RuntimeException {

	public FlightDataException(String message) {
		super(message);
	}
	
       
}
