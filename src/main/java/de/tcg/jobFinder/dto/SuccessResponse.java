package de.tcg.jobFinder.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class SuccessResponse {
	private int code;
	private LocalDateTime timestamp;
	private String message;
	private Object data;
	
	public SuccessResponse(int code, String message, Object data) {
		this.timestamp = LocalDateTime.now();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
