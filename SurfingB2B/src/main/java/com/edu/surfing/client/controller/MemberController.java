package com.edu.surfing.client.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edu.surfing.domain.member.Member;
import com.edu.surfing.exception.CustomException;
import com.edu.surfing.model.member.JwtService;
import com.edu.surfing.model.member.MemberService;
import com.edu.surfing.model.util.Message;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	private final MemberService memberService;
	private final JwtService jwtService;

	@GetMapping("/join/member-id")
	public ResponseEntity<Message> checkMemberId(String memberId) {
		log.debug("------ 아이디 중복체크 요청 ------");
		
		memberService.getMemberById(memberId);
		
		Message message = new Message();
		message.setMsg("사용가능한 아이디입니다.");
		
		log.debug("------ 아이디 중복체크 응답 ------");
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	@PostMapping("/join/member")
	public ResponseEntity<Message> regist(Member member, HttpServletRequest request){
		log.debug("------ " + member.getMemberName() + "님 회원가입 요청 ------");
		String savePath = (String) request.getSession().getServletContext().getAttribute("savePath");
		
		memberService.registMember(member, savePath);
		
		Message message = new Message();
		message.setMsg("회원가입 성공");
		
		
		log.debug("------ " + member.getMemberName() + "님 회원가입 성공 ------");
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	@PostMapping("/login/member")
	public ResponseEntity<String> handleLogin(@RequestBody Member member){
		log.debug("------ " + member.getMemberId() + "님 로그인 시도 -------");
		
		Member loginMember = memberService.getMemberByLogin(member);
		
		String token = jwtService.createToken(loginMember);
		log.debug(member.getMemberId() + "님에게 발급된 jwt:: " + token);
		
		log.debug("------ " + member.getMemberId() + "님 로그인 -------");
		return new ResponseEntity<String>(token, HttpStatus.OK);
	}

}
