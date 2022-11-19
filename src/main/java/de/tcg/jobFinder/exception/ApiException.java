package de.tcg.jobFinder.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ApiException {
	
	private final String message;
	private final int code;
	private final LocalDateTime timestamp;
	
	public ApiException(String message, HttpStatus httpStatus, LocalDateTime timestamp) {
		this.message = message;
		this.code = httpStatus.value();
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	

}
