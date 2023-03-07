package com.edu.surfing.domain.member;

import com.edu.surfing.domain.board.Board;

import lombok.Data;

/*
 *	관심 뽐내기 객체
 *	-member : 회원 객체(로그인한 회원)
 *	-board : 뽐내기 객체(로그인한 회원이 선택한 뽐내기 게시물)
 *	
 *	별도의 PK는 가지지 않으며 회원 및 게시물의 Idx를 통해 식별 
 */

@Data
public class InterestBoard {
	private Member member;
	private Board board;
}
