package com.edu.surfing.domain.menu;

import org.springframework.web.multipart.MultipartFile;

import com.edu.surfing.domain.shop.Shop;

import lombok.Data;

/*
 *	서핑샵에서 제공하는 메뉴 객체
 *	-menuIdx : 각 메뉴를 구분할 PK
 *	-shop : 서핑샵 객체(어느 샵에 어떤 상품이 있는지를 구분) 
 * 	-menuName : 상품명(상품명은 동일하나 보유 서핑샵에 따라 가격의 차이가 발생)
 * 	-menuPrice : 상품가격(위와 동일)
 * 	-menuDesc : 상품 상세설명(서핑샵마다 상이함)
 * 	-menuImage : 상품 대표이미지(등록자가 직접 선택)
 */

@Data
public class Menu {
	private int menuIdx;
//	private Shop shop; 차후 객체로 변경
	private int shopIdx;
	private String menuName;
	private int menuPrice;
	private String menuDesc;
	private String menuImage;
	
	private MultipartFile image;
}
