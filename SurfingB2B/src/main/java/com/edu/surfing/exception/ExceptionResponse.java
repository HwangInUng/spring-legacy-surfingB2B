package com.edu.surfing.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/* 예외를 클라이언트에게 응답 시 사용할 템플릿 */
@Getter
public class ExceptionResponse {
	private final HttpStatus status;
	private final String detail;
	
	public ExceptionResponse(CustomException ex) {
		this.status = ex.getStatus();
		this.detail = ex.getDetail();
	}
}
