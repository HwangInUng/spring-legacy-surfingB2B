package com.edu.surfing.model.member;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.edu.surfing.domain.oauth.KakaoMember;
import com.edu.surfing.domain.oauth.KakaoToken;
import com.edu.surfing.exception.CustomException;
import com.edu.surfing.exception.ErrorCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@PropertySource("/WEB-INF/config/api.properties")
@Component
public class OAuthService {
	@Value("${kakao.client.id}")
	private String client_id;
	@Value("${kakao.redirect.uri}")
	private String redirect_uri;

	// 인증 키 이용 kakao 토큰 발급
	public KakaoMember getSocialLoginToken(String code) throws CustomException {
		log.debug("전달할 code:: " + code);

		RestTemplate rt = new RestTemplate();
		// 헤더 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded");

		// 바디 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", client_id);
		params.add("redirect_uri", redirect_uri);
		params.add("code", code);

		// 헤더와 바디 합체
		HttpEntity<MultiValueMap<String, String>> tokenInfoRequest = new HttpEntity(params, headers);
		log.debug("현재 httpEntity 상태:: " + tokenInfoRequest);

		// 토큰 수신
		ResponseEntity<String> accessToken = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				tokenInfoRequest,
				String.class
		);
		log.debug("accessToken :: " + accessToken);
		
		// 반환데이터 매핑
		ObjectMapper objectMapper = new ObjectMapper();
		KakaoToken kakaoToken = null;
		
		try {
			kakaoToken = objectMapper.readValue(accessToken.getBody(), KakaoToken.class);
		} catch (JsonProcessingException e) { // 매핑 오류는 서버오류로 클라이언트로 통보
			log.debug(e.getMessage());
			throw new CustomException(ErrorCode.INTERNAL_ERROE);
		}
		log.debug("반환되는 token :: " + kakaoToken);

		return getMemberInfo(kakaoToken);
	}
	
	// 발급받은 Token에서 유저정보 받아오기
	public KakaoMember getMemberInfo(KakaoToken kakaoToken) {
		log.debug("넘어온 토큰은:: " + kakaoToken);
		
		// 요청 객체 생성
		RestTemplate rt = new RestTemplate();
		
		// 헤더 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + kakaoToken.getAccess_token()); // kakaoToken 정보 전달
		headers.add("property_keys", "kakao_account.profile kakao_account.name kakao_account.email");
		
		HttpEntity<MultiValueMap<String, String>> memberInfoRequest = new HttpEntity<>(headers);
		
		ResponseEntity<String> kakaoMemberInfo = rt.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				memberInfoRequest,
				String.class
		);
		log.debug("전달받은 유저정보:: " + kakaoMemberInfo.getBody());
		
//		// 반환데이터 매핑
//		ObjectMapper objectMapper = new ObjectMapper();
//		KakaoMember kakaoMember = null;
//		
//		try {
//			kakaoMember = objectMapper.readValue(kakaoMemberInfo.getBody(), KakaoMember.class);
//		} catch (JsonProcessingException e) {
//			log.debug(e.getMessage());
//			throw new CustomException(ErrorCode.INTERNAL_ERROE);
//		}
		
		return null;
	}
}
