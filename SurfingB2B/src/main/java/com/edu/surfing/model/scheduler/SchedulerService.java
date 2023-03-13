package com.edu.surfing.model.scheduler;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.edu.surfing.domain.main.SurfingSpot;
import com.edu.surfing.domain.main.Weather;
import com.edu.surfing.model.main.SurfingSpotDAO;
import com.edu.surfing.model.main.WeatherDAO;
import com.edu.surfing.model.util.APIConnector;
import com.edu.surfing.model.util.WeatherManager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchedulerService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private final SurfingSpotDAO surfingSpotDAO;
	private final WeatherDAO weatherDAO;
	private final APIConnector apiConnector;
	private final WeatherManager weatherManager;

	/* 2시간마다 기상청 API로 DB에 등록된 지역의 기상정보를 요청하는 메소드 */
	@Scheduled(cron = "0 0 0/1 * * *")
	public void registSpotWeather() throws IOException {
		log.debug("----- 날씨 정보 갱신 요청 ------");
		// DB에 저장된 모든 지역정보 조회
		List<SurfingSpot> spotList = surfingSpotDAO.selectAll();
		log.debug("----- DB에 저장된 지역의 수 : " + spotList.size() + " ------");
		
		
		// 모든 지역의 수만큼 날씨정보를 갱싱하는 반복문 수행
		for (SurfingSpot spot : spotList) {
			
			//APIConnector를 통해 지역 건당 날씨정보를 획득
			String apiResult = apiConnector.getWeatherDate(apiConnector.getWeatherURL(spot));
			
			//날씨 정보를 기반으로 weather객체리스트 생성(1개의 지역당 3일의 날씨데이터)
			List<Weather> weatherList = weatherManager.getVilageFcst(apiResult);
			log.debug(spot.getSpotName() + "의 3일 간 데이터 수는? " + weatherList.size());
			log.debug("위도 경도는? " + spot.getSpotLati() + ", " + spot.getSpotLongi());
			
			//3일의 날씨 데이터를 보유한 List를 반복문을 수행하여 DB갱신
			for (Weather weather : weatherList) {
				log.debug("현재 날씨상태? " + weather.getSky() + weather.getTmp());
				
				//spot 객체 정보를 weather 객체에 저장(idx값등 사용에 필요)
				weather.setSurfingSpot(spot);
				log.debug("추출한 weather는? " + "spot_idx=" + weather.getSurfingSpot().getSpotIdx() + "dayNo=" + weather.getDayNo());
				
				//날씨객체가 null이 아닌 경우 해당 데이터를 update
				//날씨객체가 null인 경우 해당 데이터 insert
				Weather result = weatherDAO.selectBySpot(weather);
				
				if(result == null) {
					weatherDAO.insert(weather);
				} else {
					weatherDAO.update(weather);
				}
			}
		}
		log.debug("----- 날씨 정보 갱신 완료 ------");
	}
}
