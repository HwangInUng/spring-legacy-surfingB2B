package com.edu.surfing.client.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edu.surfing.domain.main.SurfingSpot;
import com.edu.surfing.domain.main.Weather;
import com.edu.surfing.exception.SurfingSpotException;
import com.edu.surfing.model.main.SurfingSpotService;
import com.edu.surfing.model.util.Message;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SpotController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final SurfingSpotService surfingSpotService;
	
	@Transactional(rollbackFor = SurfingSpotException.class)
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
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	@GetMapping("/spot-local")
	public List getLocalList() {
		log.debug("------ 지역리스트 조회 요청 ------");
		List<String> localList = surfingSpotService.getLocalList();
		
		log.debug("------ 지역리스트" + localList.size() + "건 반환 ------");
		return localList;
	}
	
	@GetMapping("/spot-town")
	public List getTownList(String localName) {
		log.debug("------ 동네리스트 조회 요청 ------");
		List<String> townList = surfingSpotService.getTownList(localName);
		
		log.debug("------ 동네리스트" + townList.size() + "건 반환 ------");
		return townList;
	}
	
	@GetMapping("/spot")
	public List getList(String townName) {
		log.debug("------ 스팟리스트 조회 요청 ------");
		List<SurfingSpot> spotList = surfingSpotService.getList(townName);
		
		log.debug("------ 스팟리스트" + spotList.size() + "건 반환 ------");
		return spotList;
	}
	
	@PostMapping("/weather")
	public List getWeather(@RequestBody SurfingSpot surfingSpot) throws IOException {
		log.debug("------ " + surfingSpot.getSpotName() + " 날씨조회 요청 ------");
		
		List<Weather> weatherList = surfingSpotService.getWeather(surfingSpot);
		
		log.debug("------ " + surfingSpot.getSpotName() + " 날씨조회 성공 ------");
		return weatherList;
	}
	
	@ExceptionHandler(SurfingSpotException.class)
	public ResponseEntity<Message> handleException(SurfingSpotException e){
		Message message = new Message();
		message.setMsg(e.getMessage());
		
		return new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
