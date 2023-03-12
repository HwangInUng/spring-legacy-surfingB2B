package com.edu.surfing.domain.main;

import lombok.Data;

@Data
public class Weather {
	/*
	 * sky : 하늘상태
	 * pty : 강수상태(눈, 비)
	 * tmp : 1시간 기온
	 * tmn : 최저기온
	 * tmx : 최고기온
	 * wav : 파고
	 * vec : 풍향
	 * wsd : 풍속 
	 */
	private String day;
	private String sky;
	private String pty;
	private String tmp;
	private String tmn;
	private String tmx;
	private String wav;
	private String vec;
	private String wsd;
}
