package com.edu.surfing.domain.board;

import com.edu.surfing.domain.member.Member;

import lombok.Data;

/*
 *	뽐내기 리뷰 객체
 *	-reviewIdx : 리뷰 PK
 *	-member : 회원 객체(작성자)
 *	-board : 게시물 객체(리뷰의 대상)
 *	-reviewContent : 상세내용
 *	-regdate : 등록일
 */

@Data
public class BoardReview {
	private int reviewIdx;
	private Member member;
	private Board board;
	private String reviewContent;
	private String regdate;
}
