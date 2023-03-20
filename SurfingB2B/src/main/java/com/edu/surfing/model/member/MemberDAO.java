package com.edu.surfing.model.member;

import java.util.List;

import com.edu.surfing.domain.member.Member;
import com.edu.surfing.domain.oauth.OauthMember;

public interface MemberDAO {
	public List<Member> selectAll();
	public Member selectByIdx(int memberIdx);
	public Member selectById(String memberId);
	public Member selectByLogin(Member member);
	public Member selectByOauthLogin(Member member);
	public void insert(Member member);
	public void update(Member member);
	public void delete(int memberIdx);
}
