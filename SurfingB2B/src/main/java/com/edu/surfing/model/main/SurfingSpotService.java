package com.edu.surfing.model.main;

import java.io.IOException;
import java.util.List;

import com.edu.surfing.domain.main.SurfingSpot;
import com.edu.surfing.domain.main.Weather;

public interface SurfingSpotService {
	public List<SurfingSpot> getList();
	public List<SurfingSpot> getList(String townName);
	public List<String> getLocalList();
	public List<String> getTownList(String localName);
	public SurfingSpot getSpotByName(String spotName);
	public void regist(SurfingSpot surfingSpot);
	public void edit(SurfingSpot surfingSpot);
	public void remove(int spotIdx);
	public List<Weather> getWeather(SurfingSpot surfingSpot) throws IOException;
}
