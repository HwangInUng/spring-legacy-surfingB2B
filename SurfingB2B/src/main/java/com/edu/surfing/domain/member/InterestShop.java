package com.edu.surfing.domain.member;

import com.edu.surfing.domain.shop.Shop;

import lombok.Data;

/*
 *	관심 서핑샵 객체
 *	-member : 회원객체(현재 로그인한 회원)
 *	-shop : 서핑샵 객체(회원이 선택한 서핑샵 목록)
 *
 *	관심 뽐내기와 마찬가지로 PK를 보유하지 않으며
 *	회원과 서핑샵 PK를 통해 식별
 */

@Data
public class InterestShop {
	private Member member;
	private Shop shop;
}
