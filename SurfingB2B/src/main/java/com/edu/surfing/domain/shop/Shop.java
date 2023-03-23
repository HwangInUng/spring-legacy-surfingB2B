package com.edu.surfing.domain.shop;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.edu.surfing.domain.business.BusinessMember;

import lombok.Data;

/*
 *	서핑샵 객체
 *	-shopIdx : 서핑샵 PK
 *	-businessMember : 사업자회원 객체(해당 매장을 등록한 사업자 회원)
 *	-shopName : 매장명
 *	-shopCall : 매장 전화번호
 *	-shopStart : open시간(매장등록 시 선택)
 *	-shopEnd : close시간(매장등록 시 선택)
 *	-shopArea : 매장 지역(도, 광역시 단위 구분)
 *	-shopTown : 매정 동네(시, 군, 구 단위 구분)
 *	-shopLati : 위도
 *	-shopLongi : 경도
 */

@Data
public class Shop {
	private int shopIdx;
	private int businessIdx;
	private String shopName;
	private String shopCall;
	private int shopStart;
	private int shopEnd;
	private String shopArea;
	private String shopTown;
	private float shopLati;
	private float shopLongi;
	
	//collection을 이용해 전달받을 List
	private List<ShopImage> imageList;
	private List<Trainer> trainerList;
	private List<Menu> menuList;
	
	//프론트 서버로부터 전달받을 File 배열
	private MultipartFile[] images;
}
