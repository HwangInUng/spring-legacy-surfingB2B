package com.edu.surfing.domain.admin;

import lombok.Data;

/*
 * 관리자가 등록한 광고 객체
 * -adIdx : 광고번호
 * -adTitle : 광고제목
 * -adContent : 상세내용
 * -adWriter : 작성자
 * -adHit : 조회수
 * -admin : 관리자 객체
 */

@Data
public class Advertisement {
	private int adIdx;
	private String adTitle;
	private String adContent;
	private String adWirter;
	private String adHit;
	private Admin admin;
}
