package com.edu.surfing.model.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.edu.surfing.domain.main.SurfingSpot;

@Component
public class APIConnector {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	//날씨 정보 획득 메소드
	public String getWeatherDate(URL url) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setRequestMethod("GET");
		connection.setRequestProperty("Content-Type", "application/json");
		log.debug("Response code: " + connection.getResponseCode());

		BufferedReader br = null;

		if (connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300) {
			br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		} else {
			br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
		}

		// 요청 결과 출력을 위한 준비
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		// 시스템 자원 반납 및 연결해제
		br.close();
		connection.disconnect();

		String result = sb.toString();
		
		return result;
	}
	
	// 기상청 API의 url을 반환받기 위한 메소드
	public URL getWeatherURL(SurfingSpot surfingSpot) throws IOException {
		/* API 요청 URL */
		String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
		LocalDateTime now = LocalDateTime.now(); // 요청 날짜 변수
		LocalDateTime minusDay = now.minusDays(1); // 하루 전 날짜 획득
		String spotLati = surfingSpot.getSpotLati(); // 지역 x좌표
		String spotLongi = surfingSpot.getSpotLongi(); // 지역 y좌표

		String serviceKey = "Hj3sHhM%2B7wHKs685goGfKOVwhZFflMyigd1Es7cZCVXi4bP06mZR2OG6B7J1%2BbvIwzCPHQUq0xdv6VyhJs9xtQ%3D%3D";
		String pageNo = "1";
		String numOfRows = "1000";
		String dataType = "JSON";
		String base_date = minusDay.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String base_time = "2300";
		String nx = spotLati.substring(0, spotLati.indexOf("."));
		log.debug(nx);
		String ny = spotLongi.substring(0, spotLongi.indexOf("."));
		log.debug(ny);

		StringBuilder urlBuilder = new StringBuilder(apiUrl);
		
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(base_date, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode(base_time, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8"));

		/*
		 * 조합완료된 요청 URL 생성 HttpURLConnection 객체 활용 API요청
		 */
		URL url = new URL(urlBuilder.toString());
		
		return url;
	}
}
