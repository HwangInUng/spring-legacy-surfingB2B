package com.edu.surfing.domain.board;

import com.edu.surfing.domain.member.Member;

import lombok.Data;

/*
 *	뽐내기 게시물 객체
 *	-boardIdx : 게시물PK
 *	-memeber : 회원 객체(작성자)
 *	-boardTitle : 제목
 *	-boardContent : 상세내용
 *	-regdate : 게시물 등록일
 *	-boardHit : 조회수 
 */

@Data
public class Board {
	private int boardIdx;
	private Member member;
	private String boardTitle;
	private String boardContent;
	private String regdate;
	private int boardHit;
}
