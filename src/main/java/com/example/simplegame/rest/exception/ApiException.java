package com.example.simplegame.rest.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author <a href="mailto:sthallapalli@outlook.com">sthallapalli</a>
 */

class ApiException {

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Instant timestamp;
	private HttpStatus status;
	private String message;

	private ApiException() {
		timestamp = Instant.now();
	}

	ApiException(HttpStatus status) {
		this();
		this.status = status;
	}

	ApiException(HttpStatus status, Throwable ex) {
		this();
		this.status = status;
		this.message = "Unexpected error";
	}

	ApiException(HttpStatus status, String message, Throwable ex) {
		this();
		this.status = status;
		this.message = message;
	}
	
	public HttpStatus getStatus() {
		return this.status;
	}
	
	public String getMessage() {
		return this.message;
	}
}
