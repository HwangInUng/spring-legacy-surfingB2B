package com.edu.surfing.model.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.edu.surfing.domain.main.Weather;

@Component
public class WeatherManager {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	// 단기예보 반환 메소드
	public List getVilageFcst(String result) {
		// API 반환결과에서 필요데이터 추출
		JSONObject resultObj = new JSONObject(result);
		JSONObject response = resultObj.getJSONObject("response");
		JSONObject body = response.getJSONObject("body");
		JSONObject items = body.getJSONObject("items");
		JSONArray itemList = items.getJSONArray("item");
		
		// 조건에 해당하는 item을 담을 새로운 배열 생성
		JSONArray selectList = new JSONArray();
		for (int i = 0; i < itemList.length(); i++) {
			// 하나의 아이템 추출
			JSONObject item = itemList.getJSONObject(i);
			// 당일 오전 08시 기준으로 조건 분석을 위해 변수로 추출
			String fcstTime = item.getString("fcstTime");
			// 카테고리별 데이터 추출
			String category = item.getString("category");

			if (fcstTime.equals("0800")) {
				switch (category) {
				case "SKY":
					selectList.put(item);
					break;
				case "PTY":
					selectList.put(item);
					break;
				case "TMP":
					selectList.put(item);
					break;
				case "WAV":
					selectList.put(item);
					break;
				case "VEC":
					selectList.put(item);
					break;
				case "WSD":
					selectList.put(item);
					break;
				}
			}
		}
		//단기예보(3일)에 대한 날씨 DTO 생성
		Weather today = new Weather();
		Weather tomorrow = new Weather();
		Weather dayAfter = new Weather();
		Calendar calendar = Calendar.getInstance();
		LocalDateTime now = LocalDateTime.now();
		
		for(int i = 0; i < selectList.length(); i++) {
			JSONObject item = selectList.getJSONObject(i);
			
			String fcstDate = item.getString("fcstDate");
			//1~3일 간의 데이터를 추출하기 위해 조건을 통한 분리
			if(fcstDate.equals(now.format(DateTimeFormatter.ofPattern("yyyyMMdd")))) {
				putCategoryValue(today, item);
				today.setDay(Integer.toString(calendar.get(Calendar.DAY_OF_WEEK)));
				today.setDayNo(1);
			} else if(fcstDate.equals(now.plusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd")))) {
				putCategoryValue(tomorrow, item);
				tomorrow.setDayNo(2);
			} else if(fcstDate.equals(now.plusDays(2).format(DateTimeFormatter.ofPattern("yyyyMMdd")))) {
				putCategoryValue(dayAfter, item);
				dayAfter.setDayNo(3);
			}
		}
		List<Weather> weatherList = new ArrayList();
		
		weatherList.add(today);
		weatherList.add(tomorrow);
		weatherList.add(dayAfter);
		
		return weatherList;
	}

	public Weather putCategoryValue(Weather weather, JSONObject item) {
		String category = item.getString("category");
		String fcstValue = item.getString("fcstValue");

		switch (category) {
		case "SKY":
			weather.setSky(fcstValue);
			;
			break;
		case "PTY":
			weather.setPty(fcstValue);
			;
			break;
		case "TMP":
			weather.setTmp(fcstValue);
			;
			break;
		case "WAV":
			weather.setWav(fcstValue);
			;
			break;
		case "VEC":
			weather.setVec(fcstValue);
			;
			break;
		case "WSD":
			weather.setWsd(fcstValue);
			;
			break;
		}
		return weather;
	}
}
