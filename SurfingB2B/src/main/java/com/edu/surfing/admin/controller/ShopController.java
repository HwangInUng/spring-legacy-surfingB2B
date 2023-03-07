package com.edu.surfing.admin.controller;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.surfing.domain.shop.Shop;
import com.edu.surfing.exception.ShopException;
import com.edu.surfing.exception.ShopImageException;
import com.edu.surfing.exception.UploadException;
import com.edu.surfing.model.shop.ShopService;
import com.edu.surfing.util.Message;

@RestController
public class ShopController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ShopService shopService;
	
	@PostMapping("/api/shop")
	public ResponseEntity<Message> regist(Shop shop, HttpSession session){
		log.debug("------ 매장등록 요청 ------");
		log.debug("넘어온 shop 은? " + shop);
		ServletContext context = session.getServletContext();
		String savePath = context.getRealPath("/resources/data/");
		
		shopService.regist(shop, savePath);
		
		Message message = new Message();
		message.setMsg("등록 성공");
		
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.OK);
		
		log.debug("------ 매장등록 성공 ------");
		return entity;
	}
	
	@ExceptionHandler(value = {ShopException.class, ShopImageException.class, UploadException.class})
	public ResponseEntity<Message> handle(RuntimeException e){
		Message message = new Message();
		message.setMsg(e.getMessage());
		
		ResponseEntity<Message> entity = new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return entity;
	}
}
