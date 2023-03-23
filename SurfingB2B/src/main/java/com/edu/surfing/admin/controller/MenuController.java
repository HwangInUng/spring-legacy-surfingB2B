package com.edu.surfing.admin.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.surfing.domain.shop.Menu;
import com.edu.surfing.domain.shop.Shop;
import com.edu.surfing.model.shop.MenuService;
import com.edu.surfing.model.util.Message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MenuController {
	private final MenuService menuService;

	@PostMapping("/api/menu")
	public ResponseEntity<Message> regist(Menu menu, Shop shop, HttpSession session) {
		log.debug("------ 상품 등록 요청 ------");
		// 파일저장 경로 추출
		ServletContext context = session.getServletContext();
		String savePath = context.getRealPath("/resources/data/");

		menu.setShop(shop);
		menuService.regist(menu, savePath);

		Message message = new Message();
		message.setMsg(menu.getMenuName() + " 상품 등록 성공");

		log.debug("------ 상품 등록 성공 ------");
		return ResponseEntity.ok(message);
	}

	@GetMapping("/api/menu/{shopIdx}")
	public ResponseEntity<List> getListByShop(@PathVariable int shopIdx) {
		log.debug("------ 메뉴 목록 조회 요청 ------");
		return ResponseEntity.ok(menuService.getList(shopIdx));
	}

	@DeleteMapping("/api/menu/{menuIdx}")
	public ResponseEntity<Message> removeMenu(@PathVariable int menuIdx, HttpSession session) {
		log.debug("------ 메뉴 삭제 요청 ------");
		ServletContext context = session.getServletContext();
		String savePath = context.getRealPath(context.getInitParameter("savePath"));
		
		menuService.remove(menuIdx, savePath);
		
		Message message = new Message();
		message.setMsg(menuIdx + "번 삭제 성공");
		
		log.debug("------ 메뉴 삭제 성공 ------");
		return ResponseEntity.ok(message);
	}

}
