package com.edu.surfing.exception;

public class TrainerException extends RuntimeException{
	public TrainerException(String message) {
		super(message);
	}
	public TrainerException(String message, Throwable e) {
		super(message, e);
	}
}
