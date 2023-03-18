package com.edu.surfing.client.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.surfing.domain.member.Member;
import com.edu.surfing.model.member.JwtProvider;
import com.edu.surfing.model.member.MemberService;
import com.edu.surfing.model.util.Message;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	private final MemberService memberService;
	private final JwtProvider jwtProvider;

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
		
		String accessToken = memberService.getMemberByLogin(member);
		log.debug(member.getMemberId() + "님에게 발급된 jwt:: " + accessToken);
		
		/* 응답 헤더에 jwt를 저장하여 전송
		 * -'Bearer'는 인증스키마 중 하나
		 * -서버에서 'Bearer'를 포함하여 전송할 수 있지만 보안상의 이슈가 발생
		 * -토큰을 가로채어 사용할 수 있음 
		 */
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("accessToken", accessToken);
		
		log.debug("------ " + member.getMemberId() + "님 로그인 -------");
		return ResponseEntity.ok().headers(responseHeaders).body("Response tiwh header using ResponseEntity");
	}
	
	@GetMapping("/login/oauth/kakao")
	public ResponseEntity<String> handleGoogleLogin(String code){
		log.debug("넘겨받은 인증키 " + code);
		

		
		return null;
	}

}
