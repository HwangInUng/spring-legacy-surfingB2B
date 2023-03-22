package com.edu.surfing.model.oauth;

import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.edu.surfing.domain.member.Member;
import com.edu.surfing.exception.CustomException;
import com.edu.surfing.exception.ErrorCode;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@PropertySource("/WEB-INF/config/api.properties")
@Component
public class JwtProvider {
	/* 서명에 사용할 secretKey 설정은 xml에서 property로 직접등록 */
	@Value(value = "${jwt.secret}")
	private String secretKey;

	/*
	 * 토큰 생성 메소드 jwt에 저장할 회원정보를 파라미터로 전달
	 */
	public String createToken(Member member) {
		Date now = new Date(System.currentTimeMillis());
		Long expiration = 1000 * 60 * 60L;
		
		return Jwts.builder().setHeaderParam("typ", "JWT") // 토큰 타입 지정
				.setSubject("accessToken") // 토큰 제목
				.setIssuedAt(now) // 발급시간
				.setExpiration(new Date(System.currentTimeMillis() + expiration)) // 만료시간
				.claim("member", member) // 회원 아이디 저장
				.signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
				.compact();
	}

	/* 토큰 해석 메소드 */
	public Claims getMemberInfo(String token) throws CustomException {
		Jws<Claims> claims = Jwts.parser()
				.setSigningKey(secretKey.getBytes())
				.parseClaimsJws(token);
		log.debug("해석된 토큰:: " + claims.getBody());
		return claims.getBody();
	}

	/* 유효성 확인(해독된 jwt) */
	public boolean vaildToken(String token) throws CustomException {
		try {
			Claims claims = Jwts.parser()
					.setSigningKey(secretKey.getBytes())
					.parseClaimsJws(token) //토큰 파싱
					.getBody();
			return true;  //유효하다면 true 반환
		} catch (MalformedJwtException e) {
			throw new CustomException(ErrorCode.VALID_TOKEN_SIGNATURE, e);
		} catch (ExpiredJwtException e) {
			throw new CustomException(ErrorCode.VALID_TOKEN_EXPIRED, e);
		} catch (UnsupportedJwtException e) {
			throw new CustomException(ErrorCode.VALID_TOKEN_UNSUPPORTED, e);
		} catch (SignatureException e) {
			throw new CustomException(ErrorCode.VALID_TOKEN_SIGNATURE, e);
		}
	}

}