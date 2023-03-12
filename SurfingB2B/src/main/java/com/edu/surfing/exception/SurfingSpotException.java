package com.edu.surfing.exception;

public class SurfingSpotException extends RuntimeException{
	public SurfingSpotException(String message) {
		super(message);
	}
	public SurfingSpotException(String message, Throwable e) {
		super(message, e);
	}
}
