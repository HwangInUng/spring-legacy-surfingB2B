package com.edu.surfing.exception;

public class WeatherException extends RuntimeException{
	public WeatherException(String message) {
		super(message);
	}
	public WeatherException(String message, Throwable e) {
		super(message, e);
	}
}
