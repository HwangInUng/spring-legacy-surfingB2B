package com.edu.surfing.domain.reservation;

import com.edu.surfing.domain.shop.Shop;
import com.edu.surfing.domain.shop.Trainer;

import lombok.Data;

/*
 *	예약 객체
 *	-rsvIdx : 예약번호 PK
 *	-memberIdx : 회원 번호
 *	-trainer : 강사 객체(해당 예약에 선택된 강사)
 *	-selectMenu : 선택된 메뉴 객체(현재 예약에 선택된 상품들의 묶음)
 *	-shop : 서핑샵 객체(해당 예약에 선택된 서핑샵)
 *	-rsvDate : 예약일자(예약자가 선택한 일자)
 *	-rsvTime : 예약시간(예약자가 선택한 시간)
 *	-rsvMsg : 예약 시 요청사항
 *	-rsvStatus : 예약 진행상태(예정, 완료, 취소로 구분)
 */ 

@Data
public class Reservation {
	private int rsvIdx;
	private int memberIdx;
	private Trainer trainer;
	private Shop shop;
	private String rsvDate;
	private int rsvTime;
	private String rsvMsg;
	private String rsvStatus;
	private String rsvName;
	private String rsvPhone;
	
	// 클라이언트 반환값 전달을 위해 보유
	private Payment payment;
}
