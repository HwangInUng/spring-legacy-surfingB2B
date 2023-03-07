package com.edu.surfing.domain.reservation;

import lombok.Data;

/*
 *	환불내역 객체
 *	-refundIdx : 환불에 대한 PK
 *	-reservation : 예약객체(회원, 서핑샵, 강사 등의 내용을 모두 보유)
 *	-refundReason : 환불사유(대략적인 내용, 차후 분석을 위한 데이터로 활용) 
 *	-detailReason : 환불에 대한 상세사유(null이 존재할 수 있으며, 필요 시 조회)
 *	-refundName : 예금주명
 *	-refundBank : 환불은행
 *	-refundAccount : 환불계좌
 *	-refundRetopc : total price의 약자를 사용하였으며, 감액에 대한 정보
 *	-refundInfo : 감액 사유
 *	-refundPrice : 감액을 적용받은 최종 환불액
 *	-regdate : 환불이 완료된 일자
 */

@Data
public class Refund {
	private int refundIdx;
	private Reservation reservation;
	private String refundReason;
	private String detailReason;
	private String refundName;
	private String refundBank;
	private int refundAccount;
	private int refundRetopc;
	private String refundInfo;
	private int refundPrice;
	private String regdate;
}
