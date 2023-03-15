package com.edu.surfing.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.edu.surfing.exception.EmailException;
import com.edu.surfing.exception.MemberException;
import com.edu.surfing.exception.UploadException;
import com.edu.surfing.model.util.Message;

@RestControllerAdvice
public class MemberGlobalException {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(value = { MemberException.class, UploadException.class })
	public ResponseEntity<Message> handleMemberRegistException(RuntimeException e) {
		Message message = new Message();
		message.setMsg(e.getMessage());

		return new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = EmailException.class)
	public ResponseEntity<Message> handleEmailAuthException(RuntimeException e) {
		Message message = new Message();
		message.setMsg(e.getMessage());
		
		return new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
