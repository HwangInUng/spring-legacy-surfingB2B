package com.edu.surfing.client.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.surfing.domain.reservation.PaymentParams;
import com.edu.surfing.model.reservation.ReservationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/reserv")
@RestController
public class ReservationController {
	private final ReservationService reservationService;
	
	@PostMapping("/payment")
	public ResponseEntity<String> handlePayment(@RequestBody PaymentParams paymentParams){
		log.debug("------ 결제요청 :: " + paymentParams.getPaymentKey());
		reservationService.requestConfirmPayment(paymentParams);
		
		return null;
	}
}
