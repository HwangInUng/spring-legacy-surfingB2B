package com.edu.surfing.model.main;

import java.util.List;

import com.edu.surfing.domain.main.SurfingSpot;

public interface SurfingSpotDAO {
	public List<SurfingSpot> selectAll();
	public List<String> selectLocalName();
	public List<String> selectTownName(String localName);
	public List<SurfingSpot> selectByTown(String townName);
	public SurfingSpot selectByName(String spotName);
	public void insert(SurfingSpot surfingSpot);
	public void update(SurfingSpot surfingSpot);
	public void delete(int spotIdx);
}
