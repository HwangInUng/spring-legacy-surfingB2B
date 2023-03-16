package com.edu.surfing.client.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.surfing.model.member.AuthService;
import com.edu.surfing.model.util.Message;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private final AuthService AuthService;

	@GetMapping("/email")
	public ResponseEntity<Message> sendAuthEmail(String email, HttpSession session) {
		log.debug("------ " + email + " 이메일 인증 요청 ------");

		int authCode = AuthService.sendEmail(email);
		session.setAttribute("emailAuthCode", authCode);

		Message message = new Message();
		message.setMsg("이메일 전송완료, 이메일을 확인 후 인증번호를 입력해주세요.");

		log.debug("인증 코드는? " + authCode);
		log.debug("------ " + email + " 이메일 전송완료 ------");

		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	@GetMapping("/email-auth")
	public ResponseEntity<Message> checkEmailCode(String userCode, HttpSession session) {
		log.debug("------ " + userCode + " 인증번호 확인 요청 ------");

		int authCode = (Integer) session.getAttribute("emailAuthCode");
		log.debug("세션에서 불러온 코드는? " + authCode);

		Message message = new Message();
		if (Integer.parseInt(userCode) == authCode) {
			message.setMsg("인증 성공");
			message.setCode(1);
			session.removeAttribute("emailAuthCode");
		} else {
			message.setMsg("인증 실패, 다시 한번 확인해주세요.");
			message.setCode(0);
		}

		log.debug("------ 인증번호 확인 완료 ------");
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	@GetMapping("/sms")
	public ResponseEntity<Message> sendAuthSMS(String phoneNo, HttpSession session) {
		log.debug("------ " + phoneNo + "로 인증문자 전송 요청 ------");

		int authCode = AuthService.sendMessage("01091716860");
		session.setAttribute("smsAuthCode", authCode);

		Message message = new Message();
		message.setMsg("문자 전송완료, 확인 후 인증번호를 입력해주세요.");

		log.debug("------ " + phoneNo + "로 인증문자 전송 완료 ------");
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	@GetMapping("/sms-auth")
	public ResponseEntity<Message> checkSmsCode(String userCode, HttpSession session) {
		log.debug("------ " + userCode + " 인증번호 확인 요청 ------");

		int authCode = (Integer) session.getAttribute("smsAuthCode");
		log.debug("세션에서 불러온 코드는? " + authCode);

		Message message = new Message();
		if (Integer.parseInt(userCode) == authCode) {
			message.setMsg("인증 성공");
			message.setCode(1);
			session.removeAttribute("smsAuthCode");
		} else {
			message.setMsg("인증 실패, 다시 한번 확인해주세요.");
			message.setCode(0);
		}

		log.debug("------ 인증번호 확인 완료 ------");
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
}
