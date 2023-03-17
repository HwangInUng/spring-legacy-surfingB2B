package com.edu.surfing.model.member;

import java.util.List;

import com.edu.surfing.domain.member.Member;

public interface MemberService {
	public List getMemberList();
	public Member getMemberByIdx(int memberIdx);
	public Member getMemberById(String memberId);
	public String getMemberByLogin(Member member);
	public void registMember(Member member, String savePath);
	public void editMember(Member member);
	public void removeMember(int memberIdx);
}
