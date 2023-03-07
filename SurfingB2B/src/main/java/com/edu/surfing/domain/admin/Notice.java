package com.edu.surfing.domain.admin;

import lombok.Data;


/*
 *	공지사항 객체
 *	-noticeIdx : 공지사항 번호
 *	-noticeTitle : 공지사항 제목 
 *	-noticeWriter : 작성자
 *	-noticeContent : 공지내용
 *	-noticeHit : 조회수
 *	-admin : 관리자 객체
 */
@Data
public class Notice {
	private int noticeIdx;
	private String noticeTitle;
	private String noticeWriter;
	private String noticeContent;
	private int noticeHit;
	private Admin admin;
}
