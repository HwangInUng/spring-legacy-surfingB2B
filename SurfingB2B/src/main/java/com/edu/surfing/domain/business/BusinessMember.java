package com.edu.surfing.domain.business;

import com.edu.surfing.domain.member.Member;

import lombok.Data;

/*
 *	사업자 회원 객체
 *	-businessIdx : 사업자 회원 PK
 *	-member : 회원 객체(사업자로 등록될회원)
 *	-regdate : 사업자 등록일
 */

@Data
public class BusinessMember {
	private int businessIdx;
	private Member member;
	private String regdate;
}
