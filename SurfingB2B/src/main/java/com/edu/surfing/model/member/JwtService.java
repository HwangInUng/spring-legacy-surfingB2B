package com.edu.surfing.model.member;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.edu.surfing.domain.member.Member;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Setter;

@Setter
@Service
public class JwtService {
	/* 서명에 사용할 secretKey 설정은 xml에서 빈으로 직접등록 */
	private String secretKey;
	private Long expired = 1000 * 60 * 60L;
	
	/* 토큰 생성 메소드
	 * jwt에 저장할 회원정보를 파라미터로 전달
	 */
	public String createToken(Member member) {
		/* 토큰에 저장할 데이터 지정 */
		Claims claim = Jwts.claims();
		claim.put("member", member);
		
		return Jwts.builder()
				.setHeaderParam("typ", "JWT") //토큰 타입 지정
				.setSubject("memberToken")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expired))
				.claim("member", claim) //토큰에 저장할 데이터
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}
}
