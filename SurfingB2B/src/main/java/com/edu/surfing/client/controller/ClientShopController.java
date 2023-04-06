package com.edu.surfing.client.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edu.surfing.domain.business.ShopRegist;
import com.edu.surfing.domain.member.Member;
import com.edu.surfing.domain.shop.Shop;
import com.edu.surfing.model.business.ShopRegistService;
import com.edu.surfing.model.shop.ShopService;
import com.edu.surfing.model.util.Message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ClientShopController {
	private final ShopService shopService;
	private final ShopRegistService shopRegistService;
	
	@GetMapping("/shops")
	public ResponseEntity<List> getShopList(){
		log.debug("------ 서핑샵 리스트 호출 요청 ------");
		return ResponseEntity.ok(shopService.getList());
	}
	
	@GetMapping("/shop/{shopIdx}")
	public ResponseEntity<Shop> getShopInfo(@PathVariable int shopIdx){
		log.debug("------ " + shopIdx + "번 샵 정보 요청 ------");
		return ResponseEntity.ok(shopService.getDetail(shopIdx));
	}
	
	@GetMapping("/shops/{shopTown}")
	public ResponseEntity<List> getShopListBySpot(@PathVariable String shopTown){
		log.debug("------ " + shopTown + "에 위치한 샵 목록 요청 ------");
		return ResponseEntity.ok(shopService.getListBySpot(shopTown));
	}
	
	@PostMapping("/token/shop")
	public ResponseEntity<Message> handleShopRegist(@RequestBody ShopRegist shopRegist, HttpServletRequest request){
		log.debug("------ 입점신청 등록 요청 ------");
		Member member = (Member) request.getAttribute("member");
		shopRegist.setMember(member);
		
		shopRegistService.regist(shopRegist);
		
		Message message = new Message();
		message.setMsg("입점신청 완료");
		log.debug("------ 입점신청 등록 성공 ------");
		return ResponseEntity.ok(message);
	}
}
