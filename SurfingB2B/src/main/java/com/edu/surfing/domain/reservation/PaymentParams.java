package com.edu.surfing.domain.reservation;

import lombok.Getter;

@Getter
public class PaymentParams {
	private String paymentKey;
	private String orderId;
	private Long amount;
	
	//결제와 동시에 예약 및 멤버번호 주입
	private int rsvIdx;
}
