package com.edu.surfing.model.main;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.surfing.domain.main.SurfingSpot;
import com.edu.surfing.domain.main.Weather;
import com.edu.surfing.exception.SurfingSpotException;
import com.edu.surfing.model.util.APIConnector;
import com.edu.surfing.model.util.WeatherManager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SurfingSpotServiceImpl implements SurfingSpotService {
	private final SurfingSpotDAO surfingSpotDAO;
	private final APIConnector apiConnector;
	private final WeatherManager weatherManager;

	@Override
	public List<SurfingSpot> getList() {
		return surfingSpotDAO.selectAll();
	}

	@Override
	public List<SurfingSpot> getList(String townName) {
		return surfingSpotDAO.selectByTown(townName);
	}

	@Override
	public List<String> getLocalList() {
		return surfingSpotDAO.selectLocalName();
	}

	@Override
	public List<String> getTownList(String localName) {
		return surfingSpotDAO.selectTownName(localName);
	}

	@Override
	public SurfingSpot getSpotByName(String spotName) {
		return surfingSpotDAO.selectByName(spotName);
	}

	@Override
	public void regist(SurfingSpot surfingSpot) throws SurfingSpotException {
		surfingSpotDAO.insert(surfingSpot);
	}

	@Override
	public void edit(SurfingSpot surfingSpot) throws SurfingSpotException {
		surfingSpotDAO.update(surfingSpot);
	}

	@Override
	public void remove(int spotIdx) throws SurfingSpotException {
		surfingSpotDAO.delete(spotIdx);
	}

	@Override
	public List<Weather> getWeather(SurfingSpot surfingSpot) throws IOException {
		// empty List 생성(결과를 담아서 반환예정)
		List<Weather> weatherList = new ArrayList();

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
		String nx = spotLati.substring(0, spotLati.indexOf(".") - 1);
		String ny = spotLongi.substring(0, spotLongi.indexOf(".") - 1);

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

		// API Connector를 통한 api 호출 및 결과 반환
		String result = apiConnector.getWeatherDate(url);

		weatherList = weatherManager.getVilageFcst(result, minusDay);

		return weatherList;
	}
}
