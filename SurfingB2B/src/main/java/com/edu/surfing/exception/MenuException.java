package com.edu.surfing.exception;

public class MenuException extends RuntimeException{
	public MenuException(String message) {
		super(message);
	}
	public MenuException(String message, Throwable e) {
		super(message, e);
	}
}
