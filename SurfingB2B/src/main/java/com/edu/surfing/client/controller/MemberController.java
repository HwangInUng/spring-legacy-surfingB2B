package com.edu.surfing.client.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.surfing.domain.member.Member;
import com.edu.surfing.model.member.MemberService;
import com.edu.surfing.model.util.Message;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	private final MemberService memberService;

	@GetMapping("/member-id")
	public ResponseEntity<Message> checkMemberId(String memberId) {
		log.debug("------ 아이디 중복체크 요청 ------");
		
		Message message = new Message();
		
		if(memberService.getMemberById(memberId) == null) {
			message.setCode(1);
		} else {
			message.setCode(0);
		}
		
		log.debug("------ 아이디 중복체크 응답 ------");
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	@PostMapping("/member")
	public ResponseEntity<Message> regist(Member member, HttpServletRequest request){
		log.debug("------ " + member.getMemberName() + "님 회원가입 요청 ------");
		String savePath = (String) request.getSession().getServletContext().getAttribute("savePath");
		
		memberService.registMember(member, savePath);
		
		Message message = new Message();
		message.setMsg("회원가입 성공");
		
		
		log.debug("------ " + member.getMemberName() + "님 회원가입 성공 ------");
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
}
