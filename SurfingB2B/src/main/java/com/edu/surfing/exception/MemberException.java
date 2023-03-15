package com.edu.surfing.exception;

public class MemberException extends RuntimeException{
	public MemberException(String message) {
		super(message);
	}
	public MemberException(String message, Throwable e) {
		super(message, e);
	}
}
