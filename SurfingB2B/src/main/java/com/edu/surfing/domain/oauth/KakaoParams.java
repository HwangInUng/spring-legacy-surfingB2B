package com.edu.surfing.domain.oauth;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class KakaoParams implements OauthParams{
	private final String authorizationCode;
	
	public KakaoParams(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}
	
	@Override
	public OauthProvider oauthProvider() {
		return OauthProvider.KAKAO;
	}
	
	@Override
	public MultiValueMap<String, String> makeBody() {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("code", authorizationCode);
		return body;
	}
	
	@Override
	public String getAuthorizationCode() {
		return authorizationCode;
	}
}
