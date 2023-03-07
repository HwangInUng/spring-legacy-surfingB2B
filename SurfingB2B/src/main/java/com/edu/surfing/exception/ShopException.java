package com.edu.surfing.exception;

public class ShopException extends RuntimeException{
	public ShopException(String message) {
		super(message);
	}
	public ShopException(String message, Throwable e) {
		super(message, e);
	}
}
