package com.edu.surfing.domain.shop;

import lombok.Data;

/*
 *	서핑샵의 이미지 객체
 *	-imageIdx : 각 이미지들의 PK
 *	-shop : 이미지를 보유할 서핑샵 객체
 *	-imageName : 이미지 파일명
 */

@Data
public class ShopImage {
	private int imageIdx;
	private Shop shop;
	private String imageName;
}
