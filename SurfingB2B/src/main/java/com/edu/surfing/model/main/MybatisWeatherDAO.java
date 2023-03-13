package com.edu.surfing.model.main;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.edu.surfing.domain.main.SurfingSpot;
import com.edu.surfing.domain.main.Weather;
import com.edu.surfing.exception.WeatherException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisWeatherDAO implements WeatherDAO {
	private final SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List selectListBySpot(SurfingSpot surfingSpot) {
		return sqlSessionTemplate.selectList("Weather.selectListBySpot", surfingSpot);
	}

	@Override
	public Weather selectBySpot(Weather weather) {
		return sqlSessionTemplate.selectOne("Weather.selectBySpot", weather);
	}

	@Override
	public void insert(Weather weather) throws WeatherException {
		int result = sqlSessionTemplate.insert("Weather.insert", weather);
		if(result < 1) {
			throw new WeatherException("날씨 정보 등록 실패");
		}
	}

	@Override
	public void update(Weather weather) throws WeatherException {
		int result = sqlSessionTemplate.update("Weather.update", weather);
		if(result < 1) {
			throw new WeatherException("날씨 정보 업데이트 실패");
		}
	}

}
