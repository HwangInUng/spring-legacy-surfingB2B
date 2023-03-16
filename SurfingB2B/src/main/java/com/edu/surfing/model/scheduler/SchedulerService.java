package com.edu.surfing.model.scheduler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.edu.surfing.domain.main.SurfingSpot;
import com.edu.surfing.domain.main.Weather;
import com.edu.surfing.exception.CustomException;
import com.edu.surfing.exception.ErrorCode;
import com.edu.surfing.model.main.SurfingSpotDAO;
import com.edu.surfing.model.main.WeatherDAO;
import com.edu.surfing.model.util.APIConnector;
import com.edu.surfing.model.util.WeatherManager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SchedulerService {
	private final SurfingSpotDAO surfingSpotDAO;
	private final WeatherDAO weatherDAO;
	private final APIConnector apiConnector;
	private final WeatherManager weatherManager;

	/* 2시간마다 기상청 API로 DB에 등록된 지역의 기상정보를 요청하는 메소드 */
	@Transactional
	@Scheduled(cron = "0 0 0/1 * * *")
	public void registSpotWeather() {
		log.debug("----- 날씨 정보 갱신 요청 ------");
		// DB에 저장된 모든 지역정보 조회
		List<SurfingSpot> spotList = surfingSpotDAO.selectAll();
		log.debug("----- DB에 저장된 지역의 수 : " + spotList.size() + " ------");

		// 모든 지역의 수만큼 날씨정보를 갱싱하는 반복문 수행
		try {
			for (SurfingSpot spot : spotList) {

				// APIConnector를 통해 지역 건당 날씨정보를 획득
				String apiResult;
				apiResult = apiConnector.getWeatherDate(apiConnector.getWeatherURL(spot));

				// 날씨 정보를 기반으로 weather객체리스트 생성(1개의 지역당 3일의 날씨데이터)
				List<Weather> weatherList = weatherManager.getVilageFcst(apiResult);

				// 3일의 날씨 데이터를 보유한 List를 반복문을 수행하여 DB갱신
				for (Weather weather : weatherList) {

					// spot 객체 정보를 weather 객체에 저장(idx값등 사용에 필요)
					weather.setSurfingSpot(spot);

					/*
					 * 날씨객체가 null이 아닌 경우 해당 데이터를 update
					 * 날씨객체가 null인 경우 해당 데이터 insert
					 */
					Weather result = weatherDAO.selectBySpot(weather);

					if (result == null) {
						weatherDAO.insert(weather);
					} else {
						weatherDAO.update(weather);
					}
				}
			}
			/* 서버 내부 API동작 오류를 해당 서비스에서 처리하기 위함  */
		} catch (IOException | CustomException e) {
			log.error("기상정보 최신화 Exception:: ", e);
		}
		log.debug("----- 날씨 정보 갱신 완료 ------");
	}
}
