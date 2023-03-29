package com.edu.surfing.model.oauth;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.edu.surfing.domain.member.Member;
import com.edu.surfing.domain.oauth.OauthMember;
import com.edu.surfing.domain.oauth.OauthParams;
import com.edu.surfing.model.member.MemberDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@PropertySource("/WEB-INF/config/api.properties")
@RequiredArgsConstructor
@Component
public class OAuthService {
	private final MemberDAO memberDAO;
	private final RequestOauthInfoService requestOauthInfoService;
	private final JwtProvider jwtProvider;

	// 받아온 유저정보로 로그인 시도
	public String getMemberByOauthLogin(OauthParams oauthParams) {
		log.debug("------ Oauth 로그인 시도 ------");

		// 인증 파라미터 객체를 이용하여 해당 enum클래스에 해당하는 메소드 수행
		OauthMember oauthMember = requestOauthInfoService.request(oauthParams);
		log.debug("전달받은 유저 이메일:: " + oauthMember.getEmail());
		log.debug("전달받은 유저 이름:: " + oauthMember.getNickName());
		
		// 획득한 회원정보로 검증할 MemberDTO 생성
		Member accessMember = new Member();
		accessMember.setMemberId(oauthMember.getEmail());
		accessMember.setMemberName(oauthMember.getNickName());

		// 획득된 회원정보 DB 조회
		Member result = memberDAO.selectByOauthLogin(accessMember);
		log.debug("회원여부 :: " + result);
		
		// 반환할 JWT
		String accessJwt = null;

		if (result == null) {
			log.debug("------ 회원가입 필요한 회원 ------");
			// 회원가입이 되지 않은 회원이기 때문에 회원 DTO에 값을 전달하여 DB저장
			log.debug("회원가입 요청 :: " + accessMember.getMemberName());

			// kakaoMember에서 전달된 데이터를 가진 memberDTO DB 저장
			memberDAO.insert(accessMember);

			log.debug("회원가입 완료 :: " + accessMember.getMemberName());
		}
		// 이미 가입된 회원은 토큰발급
		log.debug("------ JWT 발급 ------");
		accessJwt = jwtProvider.createToken(accessMember);

		log.debug("------ JWT 발급완료 ------");
		return accessJwt;
	}
}
