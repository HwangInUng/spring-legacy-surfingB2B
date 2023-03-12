package com.edu.surfing.domain.member;

import com.edu.surfing.domain.main.SurfingSpot;

import lombok.Data;

/*
 *	관심 지역 객체
 *	-member : 회원객체(현재 로그인한 회원)
 *	-surfingSpot : 지역 객체(회원이 관심 등록한 객체)
 *
 *	다른 관심객체들과 동일 설명 생략
 */

@Data
public class InterestSpot {
	private Member member;
	private SurfingSpot surfingSpot;
}
