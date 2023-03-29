package com.edu.surfing.admin.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.surfing.domain.shop.Trainer;
import com.edu.surfing.model.shop.TrainerService;
import com.edu.surfing.model.util.Message;

@RestController
public class TrainerController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TrainerService trainerService;

	@PostMapping("/api/trainer")
	public ResponseEntity<Message> regist(Trainer trainer, HttpSession session) {
		log.debug("------ 강사등록 요청 ------");
		ServletContext context = session.getServletContext();
		String saveDir = context.getInitParameter("savePath");
		String savePath = context.getRealPath("") + saveDir;

		trainerService.regist(trainer, savePath);
		
		Message message = new Message();
		message.setMsg("강사등록 성공");

		log.debug("------ 강사등록 성공 ------");
		return ResponseEntity.ok(message);
	}
	
	@GetMapping("/api/trainer/{shopIdx}")
	public ResponseEntity<List> getListByShop(@PathVariable int shopIdx){
		log.debug("------ 강사목록 요청 ------");
		return ResponseEntity.ok(trainerService.getList(shopIdx));
	}
	
	@DeleteMapping("/api/trainer/{trainerIdx}")
	public ResponseEntity<Message> removeTrainer(@PathVariable int trainerIdx, HttpSession session){
		log.debug("------ 강사 삭제 요청 ------");
		ServletContext context = session.getServletContext();
		String saveDir = context.getInitParameter("savePath");
		String savePath = context.getRealPath("") + saveDir;
		
		trainerService.remove(trainerIdx, savePath);
		
		Message message = new Message();
		message.setMsg(trainerIdx + "번 삭제 성공");
		
		log.debug("------ 강사 삭제 성공 ------");
		return ResponseEntity.ok(message);
	}
}
