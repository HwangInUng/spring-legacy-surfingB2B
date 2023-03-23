package com.edu.surfing.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.edu.surfing.domain.shop.Shop;
import com.edu.surfing.model.shop.ShopService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AdminController {
	private final ShopService shopService;
	
	@GetMapping("/api/admin/{businessIdx}")
	public ResponseEntity<Shop> getShopInfo(@PathVariable int businessIdx){
		log.debug("------ 매장 관리자 페이지 접속 및 매장 정보 요청 ------");
		log.debug("넘어온 사업자 번호는::" + businessIdx);
		return ResponseEntity.ok(shopService.getDetailByBusiness(businessIdx));
	}
}
