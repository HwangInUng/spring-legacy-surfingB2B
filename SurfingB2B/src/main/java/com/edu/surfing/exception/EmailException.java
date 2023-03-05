package com.edu.surfing.exception;

public class EmailException extends RuntimeException{
	public EmailException(String message) {
		super(message);
	}
	public EmailException(String message, Throwable e) {
		super(message, e);
	}
}
