package com.edu.surfing.model.main;

import java.util.List;

import com.edu.surfing.domain.main.SurfingSpot;
import com.edu.surfing.domain.main.Weather;

public interface WeatherDAO {
	public List selectListBySpot(SurfingSpot surfingSpot);
	public Weather selectBySpot(Weather weather);
	public void insert(Weather weather);
	public void update(Weather weather);
}
