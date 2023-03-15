package com.edu.surfing.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.edu.surfing.exception.MenuException;
import com.edu.surfing.exception.ShopException;
import com.edu.surfing.exception.ShopImageException;
import com.edu.surfing.exception.TrainerException;
import com.edu.surfing.exception.UploadException;
import com.edu.surfing.model.util.Message;

@RestControllerAdvice
public class AdminGlobalException {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(value = {ShopException.class, ShopImageException.class, UploadException.class})
	public ResponseEntity<Message> handleShopRegistException(RuntimeException e){
		Message message = new Message();
		message.setMsg(e.getMessage());
		
		return new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = {TrainerException.class, UploadException.class})
	public ResponseEntity<Message> handleTrainerRegistException(RuntimeException e){
		Message message = new Message();
		message.setMsg(e.getMessage());
		
		return new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = {MenuException.class, UploadException.class})
	public ResponseEntity<Message> handleMenuRegistException(RuntimeException e){
		Message message = new Message();
		message.setMsg(e.getMessage());
		
		return new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
