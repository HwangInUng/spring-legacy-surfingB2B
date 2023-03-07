package com.edu.surfing.domain.business;

import com.edu.surfing.domain.member.Member;

import lombok.Data;

@Data
public class ShopRegist {
	private int registIdx;
	private Member member;
	private String shopName;
	private String shopArea;
	private String shopTown;
	private String position;
	private String shopCall;
	private String shopTool; //사용중인 프로그램
	private String regdate;
}
