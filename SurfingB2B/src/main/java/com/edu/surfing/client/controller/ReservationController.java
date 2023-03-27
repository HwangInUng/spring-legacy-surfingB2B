package com.edu.surfing.client.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.surfing.domain.member.Member;
import com.edu.surfing.domain.reservation.PaymentParams;
import com.edu.surfing.domain.reservation.Reservation;
import com.edu.surfing.domain.shop.Shop;
import com.edu.surfing.model.reservation.ReservationService;
import com.edu.surfing.model.shop.ShopService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ReservationController {
	private final ReservationService reservationService;
	private final ShopService shopService;
	
	@GetMapping("/token/reserv")
	public ResponseEntity<Member> accessReservation(HttpServletRequest request){
		log.debug("------ 예약 페이지 접근 요청 ------");
		
		Member member = (Member) request.getAttribute("member");
		
		log.debug("------ 예약 페이지 접근 허용 ------");
		return ResponseEntity.ok(member);
	}
	
	@GetMapping("/token/reserv/{shopIdx}")
	public ResponseEntity<Shop> getReservationShopInfo(@PathVariable int shopIdx){
		log.debug("------ 예약 대상 서핑샵 정보 요청 ------");
		return ResponseEntity.ok(shopService.getDetail(shopIdx));
	}
	
	@PostMapping("/token/reserv")
	public ResponseEntity<Reservation> registReservation(@RequestBody Reservation reserv){
		log.debug("------ 예약 및 결제 요청 ------");
		return null;
	}
}
