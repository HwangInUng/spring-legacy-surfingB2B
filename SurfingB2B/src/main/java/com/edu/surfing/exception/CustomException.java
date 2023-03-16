package com.edu.surfing.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {
	/* 예외처리에 필요한 code 및 메세지 */
	private final HttpStatus status;
	private final String detail;
	
	/* business exception 처리 시 사용 */
	public CustomException(ErrorCode errorCode) {
		super(errorCode.getDetail());
		
		this.status = errorCode.getStatus();
		this.detail = errorCode.getDetail();
	}
	/* business exception 처리 시 추가적인 내용이 필요할 경우
	 * ex : 상품 등록 실패 시 - new CustomException(ErrorCode.FAILED_REGIST, "상품");
	 */
	public CustomException(ErrorCode errorCode, String subject) {
		super(subject + " " + errorCode.getDetail());
		
		this.status = errorCode.getStatus();
		this.detail = subject + " " + errorCode.getDetail();
	}
	
	/* field exception 처리 시 사용 */
	public CustomException(ErrorCode errorCode, Throwable e) {
		super(errorCode.getDetail(), e);
		
		this.status = errorCode.getStatus();
		this.detail = errorCode.getDetail();
	}
}
