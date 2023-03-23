package com.edu.surfing.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.edu.surfing.domain.member.Member;
import com.edu.surfing.exception.CustomException;
import com.edu.surfing.exception.ErrorCode;
import com.edu.surfing.model.oauth.JwtProvider;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/* 로그인 인증 확인 클래스 */
@RequiredArgsConstructor
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {
	private final JwtProvider jwtProvider;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception, CustomException {
		String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		String token = authorization.replaceAll("Bearer ", "");

		// token의 값이 정상적인지 확인
		if (token != null && token.length() > 10) {
			log.debug("토큰 상태:: " + token);
			
			if (jwtProvider.vaildToken(token)) {
				ObjectMapper objectMapper = new ObjectMapper();
				
				String member = objectMapper.writeValueAsString(jwtProvider.getMemberInfo(token).get("member"));
				Member accessMember = objectMapper.readValue(member, Member.class);
				log.debug("interceptor에서 추출한 member :: " + accessMember);

				request.setAttribute("member", accessMember);
				return true;
			}
		} else {
			throw new CustomException(ErrorCode.VALID_MEMBER);
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
