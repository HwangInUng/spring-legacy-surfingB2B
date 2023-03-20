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
		
		/* 응답 헤더에 jwt를 저장하여 전송
		 * -'Bearer'는 인증스키마 중 하나
		 * -서버에서 'Bearer'를 포함하여 전송할 수 있지만 보안상의 이슈가 발생
		 * -토큰을 가로채어 사용할 수 있음 
		 */
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("accessToken", accessToken);
		
		log.debug("------ " + member.getMemberId() + "님 로그인 -------");
		return ResponseEntity.ok().headers(responseHeaders).body("Response with header using ResponseEntity");
	}
	
	@GetMapping("/oauth/kakao")
	public ResponseEntity<String> handleKakaoLogin(String code){
		log.debug("넘겨받은 Kakao 인증키 :: " + code);
		
		String accessToken = oauthService.getMemberByOauthLogin(new KakaoParams(code));
		//응답 헤더 생성
		HttpHeaders headers = new HttpHeaders();
		headers.set("accessToken", accessToken);
		
		return ResponseEntity.ok().headers(headers).body("Response with header using ResponseEntity");
	}
	
	@PostMapping("/oauth/naver")
	public ResponseEntity<String> handleNaverLogin(@RequestBody NaverParams naverParams){
		log.debug("넘겨받은 naver 인증키 :: " + naverParams.getAuthorizationCode());
		
		String accessToken = oauthService.getMemberByOauthLogin(naverParams);
		//응답 헤더 생성
		HttpHeaders headers = new HttpHeaders();
		headers.set("accessToken", accessToken);
		
		return ResponseEntity.ok().headers(headers).body("Response with header using ResponseEntity");
	}
}
