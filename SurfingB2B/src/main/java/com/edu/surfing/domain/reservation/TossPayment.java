package com.edu.surfing.domain.reservation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;

// API 응답값을 전달할 DTO
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TossPayment {
	private String orderName;
	private String method;
	private String totalAmount;
	private Card card;
	private VirtualAccount virtualAccount;
	
	@Getter
	@JsonIgnoreProperties(ignoreUnknown = true)
	public class Card{
		private String approveNo;
	}
	
	//차후 가상계좌 연동을 위해 보유
	@Getter
	@JsonIgnoreProperties(ignoreUnknown = true)
	public class VirtualAccount{
		private String bankCode;
		private String accountNumber;
		private String dueDate;
	}
}
