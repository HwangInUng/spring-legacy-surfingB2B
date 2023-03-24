package com.edu.surfing.client.controller;

import java.util.List;

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
public class ClientShopController {
	private final ShopService shopService;
	
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
}
