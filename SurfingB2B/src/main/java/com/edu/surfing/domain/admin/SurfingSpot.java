package com.edu.surfing.domain.admin;

import lombok.Data;


/*
 *	서핑지역 객체
 *	-spotIdx : 지역에 대한 PK
 *	-localName : 지역명(광역시, 도 단위)
 *	-spotName : 세부지역명(시, 군, 구 단위)
 *	-spotLati : 위도
 *	-spotLongi : 경도
 */
@Data
public class SurfingSpot {
	private int spotIdx;
	private String localName;
	private String spotName;
	private float spotLati;
	private float spotLongi;
}
