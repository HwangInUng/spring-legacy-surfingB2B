package com.edu.surfing.model.oauth;

import com.edu.surfing.domain.oauth.OauthMember;
import com.edu.surfing.domain.oauth.OauthParams;
import com.edu.surfing.domain.oauth.OauthProvider;

// 로그인 형태에 따른 동작을 위한 인터 페이스
public interface OauthClient {
	public OauthProvider oauthProvider();
	public String getOauthLoginToken(OauthParams oauthParams);
	public OauthMember getMemberInfo(String accessToken);
}
