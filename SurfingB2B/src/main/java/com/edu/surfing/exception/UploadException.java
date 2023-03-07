package com.edu.surfing.exception;

public class UploadException extends RuntimeException{
	public UploadException(String message) {
		super(message);
	}
	public UploadException(String message, Throwable e) {
		super(message, e);
	}
}
