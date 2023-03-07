package com.edu.surfing.domain.shop;

import com.edu.surfing.domain.member.Member;
import com.edu.surfing.domain.reservation.Reservation;

import lombok.Data;

/*
 *	서핑샵 리뷰 객체
 *	-reviewIdx : 리뷰 PK
 *	-reviewContent : 상세내용
 *	-regdate : 리뷰 등록일
 *	-reviewScore : 평점(서핑샵의 등록된 리뷰와 평점의 총합을 나누어 매장 평점 계산)
 *	-reservation : 예약 객체(해당 예약에 대한 리뷰만 작성가능)
 *	-member : 리뷰를 작성한 회원 객체
 */

@Data
public class ShopReview {
	private int reviewIdx;
	private String reviewContent;
	private String regdate;
	private int reviewScore;
	private Reservation reservation;
	private Member member;
}
