package com.edu.surfing.domain.admin;

import lombok.Data;

/*
 * 관리자 객체
 * -adminIdx : 관리자 PK
 * -adminId : 관리자 ID
 * -adminPass : 관리자 비밀번호
 * -regdate : 관리자 등록일
 * -adminAuth : 권한(차후 권한에 따라 관리항목을 분류하는 것을 고려)
 */

@Data
public class Admin {
	private int adminIdx;
	private String adminId;
	private String adminPass;
	private String regdate;
	private String adminAuth;
}
