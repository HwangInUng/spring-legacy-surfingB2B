package com.edu.surfing.domain.reservation;

import lombok.Getter;

@Getter
public class PaymentParams {
	private String paymentKey;
	private String orderId;
	private Long amount;
}
