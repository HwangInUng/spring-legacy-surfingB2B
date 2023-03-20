package com.edu.surfing.domain.member;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/*
 *	회원 객체
 *	-memberIdx : 회원 PK
 *	-memberId : 회원 로그인 ID
 *	-memberPass : 회원 비밀번호
 *	-memberName : 회원 이름
 *	-localAddress : 지역주소(도, 광역시 단위)
 *	-detailAddress : 상세주소(번지, 동/호수 단위)
 *	-phoneNo : 회원의 전화번호
 *	-email : 회원 이메일
 *	-regdate : 회원 등록일
 *	-profileImage : 회원 프로필 이미지
 */

@Data
public class Member{
	private int memberIdx;
	private String memberId;
	private String memberPass;
	private String memberName;
	private String localAddress;
	private String detailAddress;
	private String phoneNo;
	private String email;
	private String profileImage;
	private String regdate;
	
	private MultipartFile image;
}
