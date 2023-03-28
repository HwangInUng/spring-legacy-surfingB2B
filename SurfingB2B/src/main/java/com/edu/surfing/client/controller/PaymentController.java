package com.edu.surfing.client.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edu.surfing.domain.reservation.PaymentParams;
import com.edu.surfing.domain.reservation.Reservation;
import com.edu.surfing.model.reservation.ReservationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PaymentController {
	private final ReservationService reservationService;
	
	@GetMapping("/token/payment")
	public ResponseEntity<String> accessPayment(){
		log.debug("------ 결제페이지 접근 요청 ------");
		log.debug("------ 결제페이지 접근 허용 ------");
		return ResponseEntity.ok("");
	}
	
	@PostMapping("/token/payment")
	public ResponseEntity<Reservation> handlePayment(@RequestBody PaymentParams paymentParams){
		log.debug("------ 예약 및 결제요청 :: " + paymentParams.getPaymentKey());
		
		Reservation reservation = reservationService.requestConfirmPayment(paymentParams);
		log.debug("------ 결제완료, 예약정보반환 :: " + reservation.getRsvName());
		return ResponseEntity.ok(reservation);
	}
}
