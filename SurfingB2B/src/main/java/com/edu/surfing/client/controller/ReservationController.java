package com.edu.surfing.client.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.surfing.domain.member.Member;
import com.edu.surfing.domain.reservation.PaymentParams;
import com.edu.surfing.model.reservation.ReservationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ReservationController {
	private final ReservationService reservationService;
	
	@GetMapping("/token/reserv")
	public ResponseEntity<Member> accessReservation(HttpServletRequest request){
		log.debug("------ 예약 페이지 접근 요청 ------");
		
		Member member = (Member) request.getAttribute("member");
		
		log.debug("------ 예약 페이지 접근 허용 ------");
		return ResponseEntity.ok(member);
	}
	
	@GetMapping("/token/reserv/payment")
	public ResponseEntity<String> accessPayment(){
		log.debug("------ 결제페이지 접근 요청 ------");
		
		log.debug("------ 결제페이지 접근 허용 ------");
		return ResponseEntity.ok("");
	}
	@PostMapping("/token/reserv/payment")
	public ResponseEntity<String> handlePayment(@RequestBody PaymentParams paymentParams){
		log.debug("------ 결제요청 :: " + paymentParams.getPaymentKey());
		
		reservationService.requestConfirmPayment(paymentParams);
		
		return null;
	}
}
