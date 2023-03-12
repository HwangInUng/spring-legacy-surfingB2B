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
}
