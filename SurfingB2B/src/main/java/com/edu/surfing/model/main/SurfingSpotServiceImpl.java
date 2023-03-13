package com.edu.surfing.model.main;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.surfing.domain.main.SurfingSpot;
import com.edu.surfing.domain.main.Weather;
import com.edu.surfing.exception.SurfingSpotException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SurfingSpotServiceImpl implements SurfingSpotService {
	private final SurfingSpotDAO surfingSpotDAO;
	private final WeatherDAO weatherDAO;

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
	public List<Weather> getWeather(SurfingSpot surfingSpot) {
		return weatherDAO.selectListBySpot(surfingSpot);
	}
}
