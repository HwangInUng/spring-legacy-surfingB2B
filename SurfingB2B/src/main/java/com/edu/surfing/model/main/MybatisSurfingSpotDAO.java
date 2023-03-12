package com.edu.surfing.model.main;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.edu.surfing.domain.main.SurfingSpot;
import com.edu.surfing.exception.SurfingSpotException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisSurfingSpotDAO implements SurfingSpotDAO {
	private final SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<SurfingSpot> selectAll() {
		return sqlSessionTemplate.selectList("SurfingSpot.selectAll");
	}
	
	@Override
	public List<String> selectLocalName() {
		return sqlSessionTemplate.selectList("SurfingSpot.selectLocalName");
	}
	
	@Override
	public List<String> selectTownName(String localName) {
		return sqlSessionTemplate.selectList("SurfingSpot.selectTownName", localName);
	}
	
	@Override
	public List<SurfingSpot> selectByTown(String townName) {
		return sqlSessionTemplate.selectList("SurfingSpot.selectByTown", townName);
	}

	@Override
	public SurfingSpot selectByName(String spotName) {
		return sqlSessionTemplate.selectOne("SurfingSpot.selectByName", spotName);
	}

	@Override
	public void insert(SurfingSpot surfingSpot) throws SurfingSpotException {
		int result = sqlSessionTemplate.insert("SurfingSpot.insert", surfingSpot);
		if(result < 1) {
			throw new SurfingSpotException("지역등록 실패");
		}
	}

	@Override
	public void update(SurfingSpot surfingSpot) throws SurfingSpotException {
		int result = sqlSessionTemplate.update("SurfingSpot.update", surfingSpot);
		if(result < 1) {
			throw new SurfingSpotException("지역수정 실패");
		}
	}

	@Override
	public void delete(int spotIdx) throws SurfingSpotException {
		int result = sqlSessionTemplate.delete("SurfingSpot.delete", spotIdx);
		if(result < 1) {
			throw new SurfingSpotException("지역삭제 실패");
		}

	}

}
