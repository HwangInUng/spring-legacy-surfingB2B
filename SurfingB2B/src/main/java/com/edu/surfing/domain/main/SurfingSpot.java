package com.edu.surfing.domain.main;

import lombok.Data;


/*
 *	서핑지역 객체
 *	-spotIdx : 지역에 대한 PK
 *	-localName : 지역명(광역시, 도 단위)
 *	-townName: 세부지역명(시, 군, 구 단위)
 *	-spotName : 해당위치명(읍, 면, 동 단위)
 *	-spotLati : 위도
 *	-spotLongi : 경도
 */
@Data
public class SurfingSpot {
	private int spotIdx;
	private String localName;
	private String townName;
	private String spotName;
	private String spotLati;
	private String spotLongi;
}
