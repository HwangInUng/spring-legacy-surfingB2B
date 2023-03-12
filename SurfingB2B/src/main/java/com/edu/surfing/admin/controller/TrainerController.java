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

import com.edu.surfing.domain.shop.Trainer;
import com.edu.surfing.exception.TrainerException;
import com.edu.surfing.exception.UploadException;
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
		log.debug("넘어온 trainer 는? " + trainer);
		ServletContext context = session.getServletContext();
		String savePath = context.getRealPath("/resources/data/");

		trainerService.regist(trainer, savePath);
		
		Message message = new Message();
		message.setMsg("강사등록 성공");
		

		log.debug("------ 강사등록 성공 ------");
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	@ExceptionHandler(value = { TrainerException.class, UploadException.class })
	public ResponseEntity<Message> handleException(RuntimeException e) {
		Message message = new Message();
		message.setMsg(e.getMessage());
		
		return new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
