package com.edu.surfing.client.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.surfing.model.shop.ShopService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MainPageController {
	private final ShopService shopSerivce;
	
	@GetMapping("/main/popular")
	public ResponseEntity<List> getPopularShopList(){
		log.debug("------ 인기 서핑샵 리스트 호출 ------");
		return ResponseEntity.ok(shopSerivce.getPopularShop());
	}
}
