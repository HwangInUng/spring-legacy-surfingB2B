package com.edu.surfing.exception;

/*
 * enum 생성자에 사용하기 위하여 static으로 import하여 사용
 * 프로젝트에서 발생할 수 있는 예외 HttpStatus를 판단하여 적용 및 추가
 */
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {

	/* 400 : 잘못된 문법으로 인하여 서버가 요청 이해불가 */
	FAILED_FILE_ERROR(INTERNAL_SERVER_ERROR, "선택된 이미지가 없습니다. 다시 시도해주세요."),
	MISMATCH_LOGIN_INFO(BAD_REQUEST, "로그인 정보가 올바르지 않습니다."),
	FAILED_REGIST(BAD_REQUEST, "등록 정보가 올바르지 않습니다."),
	FAILED_UPDATE(BAD_REQUEST, "수정 정보가 올바르지 않습니다."),

	/* 401 : 비인증을 의미하며 요청한 응답을 받기 위해 인증이 필요 */

	/* 403 : 접근권한 미보유, 접근하려는 클라이언트가 미승인으로 서버가 거절 */
	VALID_TOKEN_SIGNATURE(FORBIDDEN, "유효한 토큰이 아닙니다."),
	VALID_TOKEN_EXPIRED(FORBIDDEN, "만료된 토큰입니다."),
	VALID_TOKEN_UNSUPPORTED(FORBIDDEN, "지원되지 않는 토큰입니다."),
	VALID_MEMBER(FORBIDDEN, "로그인 후 이용가능한 서비스 입니다."),
	VALID_BUSINESS_MEMBER(FORBIDDEN, "사업자회원만 이용가능한 서비스 입니다."),

	/* 404 : 해당 Resource를 찾을 수 없는상태 */
	NOT_FOUND_DELETE(NOT_FOUND, "삭제할 대상이 올바르지 않습니다."),
	NOT_FOUND_AUTHCODE(NOT_FOUND, "인증번호가 올바르지 않습니다."),

	/* 409 : 중복된 데이터가 존재하는 경우 사용 */
	DUPLICATION_ID(CONFLICT, "이미 존재하는 ID 입니다."),

	/* 500 : 서버 내부오류 */
	INTERNAL_FILE_ERROR(INTERNAL_SERVER_ERROR, "이미지 등록이 실패하였습니다. 다시 시도해주세요."),
	INTERNAL_FILE_DELETE_ERROR(INTERNAL_SERVER_ERROR, "이미지 삭제가 실패하였습니다. 다시 시도해주세요."),
	INTERNAL_SEND_ERROR(INTERNAL_SERVER_ERROR, "전송이 실패하였습니다. 다시 시도해주세요."),
	INTERNAL_API_ERROR(INTERNAL_SERVER_ERROR, "API 요청이 실패하였습니다."),
	INTERNAL_ERROE(INTERNAL_SERVER_ERROR, "서버 내부오류. 다시 시도해주세요."),
	INTERNAL_CONVERT_PASSWORD_ERROR(INTERNAL_SERVER_ERROR, "비밀번호 저장에 실패하였습니다.");
	
	
	/* 생성자로 값을 주입받고, Getter를 이용해 접근*/
	private final HttpStatus status;
	private final String detail;
	
	ErrorCode(HttpStatus status, String detail){
		this.status = status;
		this.detail = detail;
	}
	
	public String getCustomDetail(String msg) {
		return msg + detail;
	}
}
