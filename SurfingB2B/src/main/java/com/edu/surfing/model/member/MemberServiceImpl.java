package com.edu.surfing.model.member;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.surfing.domain.member.Member;
import com.edu.surfing.exception.CustomException;
import com.edu.surfing.exception.ErrorCode;
import com.edu.surfing.model.oauth.JwtProvider;
import com.edu.surfing.model.util.FileManager;
import com.edu.surfing.model.util.PasswordConverter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberDAO memberDAO;
	private final FileManager fileManager;
	private final JwtProvider jwtProvider;

	@Override
	public List getMemberList() {
		return memberDAO.selectAll();
	}

	@Override
	public Member getMemberByIdx(int memberIdx) {
		return memberDAO.selectByIdx(memberIdx);
	}

	@Override
	public Member getMemberById(String memberId) throws CustomException {
		return memberDAO.selectById(memberId);
	}

	@Override
	public String getMemberByLogin(Member member) throws CustomException {
		//로그인 비밀번호 암호화
		String memberPass = PasswordConverter.getCovertedPassword(member.getMemberPass());
		member.setMemberPass(memberPass);
		
		//해당 멤버정보 DB 일치여부 조회
		Member loginMember = memberDAO.selectByLogin(member);
		log.debug("사업자 회원여부:: " + loginMember.getBusinessMember());
		
		//조건 판단을 통한 토큰 발급
		if(loginMember != null) {
			return jwtProvider.createToken(loginMember);
		} else {
			throw new CustomException(ErrorCode.MISMATCH_LOGIN_INFO);
		}
	}
	
	@Override
	public void registMember(Member member, String savePath) throws CustomException {
		// 비밀번호 암호화
		String memberPass = PasswordConverter.getCovertedPassword(member.getMemberPass());
		
		// 파라미터로 전달받은 바이너리 파일을 이용하여 이미지명 반환
		String imageName = fileManager.getSaveFileName(member.getImage(), savePath);
		
		// 이미지 및 비밀번호 세팅
		member.setProfileImage(imageName);
		member.setMemberPass(memberPass);
		
		memberDAO.insert(member);
	}

	@Override
	public void editMember(Member member) throws CustomException {
		memberDAO.update(member);
	}

	@Override
	public void removeMember(int memberIdx) throws CustomException {
		memberDAO.delete(memberIdx);
	} 
}
