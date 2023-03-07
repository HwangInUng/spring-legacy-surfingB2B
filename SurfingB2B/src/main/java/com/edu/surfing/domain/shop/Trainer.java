package com.edu.surfing.domain.shop;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/*
 *	강사 객체
 *	-trainerIdx : 강사 PK
 *	-trainerName : 강사명
 *	-trainerCareer : 경력
 *	-trainerType : 강사의 서핑유형(퍼포먼스, 클래식 등)
 *	-trainerBoard : 강사가 자주이용하는 서핑보드
 *	-regdate : 강사 등록일
 *	-trainerImage : 강사 프로필에 등록될 이미지 파일명
 *	-shop : 강사가 소속된 서핑샵 객체
 */

@Data
public class Trainer {
	private int trainerIdx;
	private String trainerName;
	private int trainerCareer;
	private String trainerType;
	private String trainerBoard;
	private String regdate;
	private String trainerImage;
	private Shop shop;
	
	private MultipartFile image;
}
