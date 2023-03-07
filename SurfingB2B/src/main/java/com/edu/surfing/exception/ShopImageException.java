package com.edu.surfing.exception;

public class ShopImageException extends RuntimeException{
	public ShopImageException(String message) {
		super(message);
	}
	public ShopImageException(String message, Throwable e) {
		super(message, e);
	}
}
