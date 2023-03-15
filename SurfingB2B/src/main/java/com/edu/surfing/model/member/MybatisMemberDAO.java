package com.edu.surfing.model.member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.edu.surfing.domain.member.Member;
import com.edu.surfing.exception.MemberException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisMemberDAO implements MemberDAO{
	private final SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<Member> selectAll() {
		return sqlSessionTemplate.selectList("Member.selectAll");
	}

	@Override
	public Member selectByIdx(int memberIdx) {
		return sqlSessionTemplate.selectOne("Member.selectByIdx", memberIdx);
	}

	@Override
	public Member selectById(String memberId) {
		return sqlSessionTemplate.selectOne("Member.selectById", memberId);
	}

	@Override
	public Member selectByLogin(Member member) {
		return sqlSessionTemplate.selectOne("Member.selectByLogin", member);
	}

	@Override
	public void insert(Member member) throws MemberException {
		int result = sqlSessionTemplate.insert("Member.insert", member);
		if(result < 1) {
			throw new MemberException("회원가입 실패. 입력된 정보를 다시 확인해주세요.");
		}
	}

	@Override
	public void update(Member member) throws MemberException {
		int result = sqlSessionTemplate.update("Member.update", member);
		if(result < 1) {
			throw new MemberException("프로필 수정 실패. 정보를 다시 확인해주세요.");
		}
	}

	@Override
	public void delete(int memberIdx) throws MemberException {
		int result = sqlSessionTemplate.delete("Member.delete", memberIdx);
		if(result < 1) {
			throw new MemberException("삭제 실패. 다시 시도해주세요.");
		}
	}

}
