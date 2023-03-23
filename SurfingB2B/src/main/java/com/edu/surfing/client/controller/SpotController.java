package com.edu.surfing.client.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edu.surfing.domain.main.SurfingSpot;
import com.edu.surfing.domain.main.Weather;
import com.edu.surfing.exception.CustomException;
import com.edu.surfing.model.main.SurfingSpotService;
import com.edu.surfing.model.util.Message;

import lombok.RequiredArgsConstructor;
import retrofit2.http.Path;

@RestController
@RequiredArgsConstructor
public class SpotController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final SurfingSpotService surfingSpotService;
	
	@Transactional(rollbackFor = CustomException.class)
	@PostMapping("/spot")
	public ResponseEntity<Message> regist(@RequestBody List<SurfingSpot> surfingSpotList){
		log.debug("------ 지역 등록 요청 ------");
		log.debug("요청받은 파라미터 : " + surfingSpotList);
		
		for(SurfingSpot spot : surfingSpotList) {
			surfingSpotService.regist(spot);
		}
		
		Message message = new Message();
		message.setMsg("지역등록 성공");
		
		log.debug("------ 지역 등록 성공 ------");
		return ResponseEntity.ok(message);
	}
	
	@GetMapping("/spot-local")
	public ResponseEntity<List> getLocalList() {
		log.debug("------ 지역리스트 조회 요청 ------");
		List<String> localList = surfingSpotService.getLocalList();
		
		log.debug("------ 지역리스트" + localList.size() + "건 반환 ------");
		return ResponseEntity.ok(localList);
	}
	
	@GetMapping("/spot-town/{localName}")
	public ResponseEntity<List> getTownList(@PathVariable String localName) {
		log.debug("------ 동네리스트 조회 요청 ------");
		List<String> townList = surfingSpotService.getTownList(localName);
		
		log.debug("------ 동네리스트" + townList.size() + "건 반환 ------");
		return ResponseEntity.ok(townList);
	}
	
	@GetMapping("/spot/{townName}")
	public ResponseEntity<List> getList(@PathVariable String townName) {
		log.debug("------ 스팟리스트 조회 요청 ------");
		List<SurfingSpot> spotList = surfingSpotService.getList(townName);
		
		log.debug("------ 스팟리스트" + spotList.size() + "건 반환 ------");
		return ResponseEntity.ok(spotList);
	}
	
	@PostMapping("/weather")
	public ResponseEntity<List> getWeather(@RequestBody SurfingSpot surfingSpot) {
		long startTime = System.currentTimeMillis();
		log.debug("------ " + surfingSpot.getSpotName() + " 날씨조회 요청 ------");
		
		List<Weather> weatherList = surfingSpotService.getWeather(surfingSpot);
		
		log.debug("------ " + surfingSpot.getSpotName() + " 날씨조회 성공 ------");
		return ResponseEntity.ok(weatherList);
	}
	
}
