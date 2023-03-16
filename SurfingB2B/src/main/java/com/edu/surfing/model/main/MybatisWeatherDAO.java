package com.edu.surfing.model.main;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.edu.surfing.domain.main.SurfingSpot;
import com.edu.surfing.domain.main.Weather;
import com.edu.surfing.exception.CustomException;
import com.edu.surfing.exception.ErrorCode;

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
	public void insert(Weather weather) throws CustomException {
		int result = sqlSessionTemplate.insert("Weather.insert", weather);
		if(result < 1) {
			throw new CustomException(ErrorCode.INTERNAL_API_ERROR, "기상청");
		}
	}

	@Override
	public void update(Weather weather) throws CustomException {
		int result = sqlSessionTemplate.update("Weather.update", weather);
		if(result < 1) {
			throw new CustomException(ErrorCode.INTERNAL_API_ERROR, "기상청");
		}
	}

}
