package com.edu.surfing.model.member;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.edu.surfing.domain.member.Member;
import com.edu.surfing.domain.shop.ShopImage;
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
		// 로그인 비밀번호 암호화
		String memberPass = PasswordConverter.getCovertedPassword(member.getMemberPass());
		member.setMemberPass(memberPass);
		log.debug("로그인 시도 :: " + member);
		// 해당 멤버정보 DB 일치여부 조회
		Member loginMember = memberDAO.selectByLogin(member);
		log.debug("사업자 회원여부:: " + loginMember.getBusinessMember());

		return jwtProvider.createToken(loginMember);
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
	public void editMember(Member member, String savePath) throws CustomException {
		if(member.getMemberPass() != null) {
			String memberPass = PasswordConverter.getCovertedPassword(member.getMemberPass());
			member.setMemberPass(memberPass);
		}
		
		// 수정할 파일 조회
		MultipartFile file = member.getImage();
		log.debug("file 상태 :: " + file);
		
		// 파일이 있는 경우 파일삭제
		if (file != null) {
			if (fileManager.removeImage(member.getProfileImage(), savePath)) {
				member.setProfileImage(fileManager.getSaveFileName(file, savePath));

			}else {
				throw new CustomException(ErrorCode.FAILED_FILE_ERROR);
			}
		}
		memberDAO.update(member);

	}

	@Override
	public void removeMember(int memberIdx) throws CustomException {
		memberDAO.delete(memberIdx);
	}
}
