package com.edu.surfing.admin.controller;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edu.surfing.domain.shop.Shop;
import com.edu.surfing.model.shop.ShopService;
import com.edu.surfing.model.util.Message;
import com.sun.mail.iap.Response;

@RestController
public class ShopController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ShopService shopService;
	
	@PostMapping("/api/shop")
	public ResponseEntity<Message> regist(Shop shop, HttpSession session){
		log.debug("------ 매장등록 요청 ------");
		log.debug("넘어온 shop 은? " + shop);
		String savePath = session.getServletContext().getInitParameter("savePath");
		
		shopService.regist(shop, savePath);
		
		Message message = new Message();
		message.setMsg("등록 성공");
		
		
		log.debug("------ 매장등록 성공 ------");
		return ResponseEntity.ok(message);
	}
	
	@PostMapping("/api/shop-edit")
	public ResponseEntity<Message> edit(Shop shop, HttpSession session){
		log.debug("------ 매장 정보 수정 요청 ------");
		
		Message message = new Message();
		if(shop.getImages() != null) { //수정해야하는 이미지가 있는 경우
			ServletContext context = session.getServletContext();
			String savePath = context.getRealPath(context.getInitParameter("savePath"));
			
			shopService.editWithImage(shop, savePath);
			message.setMsg("매장정보 및 이미지 수정 완료");
		} else { //내용만 수정해야하는 경우
			shopService.edit(shop);
			message.setMsg("매장정보 수정 완료");
		}
		
		
		log.debug("------ 매장 정보 수정 성공 ------");
		return ResponseEntity.ok(message);
	}
	
}
