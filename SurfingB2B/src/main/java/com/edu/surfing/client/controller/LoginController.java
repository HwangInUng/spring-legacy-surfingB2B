package com.edu.surfing.client.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.surfing.domain.member.Member;
import com.edu.surfing.domain.oauth.KakaoMember;
import com.edu.surfing.domain.oauth.KakaoToken;
import com.edu.surfing.model.member.MemberService;
import com.edu.surfing.model.member.OAuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
	private final MemberService memberService;
	private final OAuthService oauthService;
	
	@PostMapping("/member")
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
	
	@GetMapping("/oauth/kakao")
	public ResponseEntity<KakaoMember> handleKakaoLogin(String code){
		log.debug("넘겨받은 Kakao 인증키 :: " + code);
		
		KakaoMember kakaoMember = oauthService.getSocialLoginToken(code);
		
		return new ResponseEntity<KakaoMember>(kakaoMember, HttpStatus.OK);
	}
}
