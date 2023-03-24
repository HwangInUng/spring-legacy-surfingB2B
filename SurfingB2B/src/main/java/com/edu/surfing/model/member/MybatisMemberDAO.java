package com.edu.surfing.model.member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.edu.surfing.domain.member.Member;
import com.edu.surfing.domain.oauth.KakaoMember;
import com.edu.surfing.domain.oauth.OauthMember;
import com.edu.surfing.exception.CustomException;
import com.edu.surfing.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisMemberDAO implements MemberDAO {
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
	public Member selectById(String memberId) throws CustomException {
		Member member = sqlSessionTemplate.selectOne("Member.selectById", memberId);
		if (member != null) {
			throw new CustomException(ErrorCode.DUPLICATION_ID);
		}
		return member;
	}

	@Override
	public Member selectByLogin(Member member) throws CustomException{
		Member result = sqlSessionTemplate.selectOne("Member.selectByLogin", member);
		if(result == null) {
			throw new CustomException(ErrorCode.MISMATCH_LOGIN_INFO);
		}
		return result;
	}

	@Override
	public Member selectByOauthLogin(Member member) {
		return sqlSessionTemplate.selectOne("Member.selectByOauthLogin", member);
	}

	@Override
	public void insert(Member member) throws CustomException {
		int result = sqlSessionTemplate.insert("Member.insert", member);
		if (result < 1) {
			throw new CustomException(ErrorCode.FAILED_REGIST, "회원");
		}
	}

	@Override
	public void update(Member member) throws CustomException {
		int result = sqlSessionTemplate.update("Member.update", member);
		if (result < 1) {
			throw new CustomException(ErrorCode.FAILED_UPDATE, "회원");
		}
	}

	@Override
	public void delete(int memberIdx) throws CustomException {
		int result = sqlSessionTemplate.delete("Member.delete", memberIdx);
		if (result < 1) {
			throw new CustomException(ErrorCode.NOT_FOUND_DELETE);
		}
	}

}
