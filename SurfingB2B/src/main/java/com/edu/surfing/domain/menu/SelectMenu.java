package com.edu.surfing.domain.menu;

import com.edu.surfing.domain.member.Member;
import com.edu.surfing.domain.shop.Shop;
import com.edu.surfing.domain.shop.Trainer;

import lombok.Data;

/*
 *	예약시 선택한 메뉴 객체
 *	-selectIdx : 선택한 메뉴를 구분하기 위한 PK
 *	-member : 회원 객체(해당 메뉴를 선택한 회원)
 *	-shop : 서핑샵 객체(해당 상품을 보유한 서핑샵)
 *	-trainer : 강사 객체(해당 상품을 수강받을 강사객체, 렌탈 시 null 적용)
 *	-menu : 상품 객체(선택한 상품 각각의 정보를 받아올 객체)
 *	-menuCount : 상품별 갯수를 지정(예약 시 총 가격을 산출하는 근거)
 * */

@Data
public class SelectMenu {
	private int selectIdx;
	private Member member;
	private Shop shop;
	private Trainer trainer;
	private Menu menu;
	private int menuCount;
}
