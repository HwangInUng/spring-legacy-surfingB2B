package com.edu.surfing.domain.oauth;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class NaverParams implements OauthParams{
	// Post 요청 시 파라미터로 전달
	private String authorizationCode;
	private String state;
	
	@Override
	public OauthProvider oauthProvider() {
		return OauthProvider.NAVER; // Enum 자료형 지정
	}
	
	@Override
	public MultiValueMap<String, String> makeBody() {
		// Body 지정
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("code", authorizationCode);
		body.add("state", state);
		return body;
	}
}
