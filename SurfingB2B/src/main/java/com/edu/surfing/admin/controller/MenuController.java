package com.edu.surfing.admin.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.surfing.domain.menu.Menu;
import com.edu.surfing.model.menu.MenuService;
import com.edu.surfing.model.util.Message;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MenuController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private final MenuService menuService;
	
	@PostMapping("/api/menu")
	public ResponseEntity<Message> regist(Menu menu, HttpSession session){
		log.debug("------ 상품 등록 요청 ------");
		//파일저장 경로 추출
		ServletContext context = session.getServletContext();
		String savePath = context.getRealPath("/resources/data/");
		
		menuService.regist(menu, savePath);
		
		Message message = new Message();
		message.setMsg(menu.getMenuName() + " 상품 등록 성공");
		
		log.debug("------ 상품 등록 성공 ------");
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

}
