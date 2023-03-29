package com.edu.surfing.client.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.surfing.domain.member.Member;
import com.edu.surfing.domain.oauth.KakaoParams;
import com.edu.surfing.domain.oauth.NaverParams;
import com.edu.surfing.model.member.MemberService;
import com.edu.surfing.model.oauth.OAuthService;

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
		
		log.debug("------ " + member.getMemberId() + "님 로그인 -------");
		return ResponseEntity.ok(accessToken);
	}
	
	@PostMapping("/oauth/kakao")
	public ResponseEntity<String> handleKakaoLogin(@RequestBody KakaoParams kakaoParams){
		log.debug("넘겨받은 Kakao 인증키 :: " + kakaoParams.getAuthorizationCode());
		
		String accessToken = oauthService.getMemberByOauthLogin(kakaoParams);
		return ResponseEntity.ok(accessToken);
	}
	
	@PostMapping("/oauth/naver")
	public ResponseEntity<String> handleNaverLogin(@RequestBody NaverParams naverParams){
		log.debug("넘겨받은 naver 인증키 :: " + naverParams.getAuthorizationCode());
		
		String accessToken = oauthService.getMemberByOauthLogin(naverParams);
		return ResponseEntity.ok(accessToken);
	}
}
