package com.edu.surfing.model.shop;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.surfing.domain.shop.Trainer;
import com.edu.surfing.exception.TrainerException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisTrainerDAO implements TrainerDAO {
	private final SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<Trainer> selectAll() {
		return sqlSessionTemplate.selectList("Trainer.selectAll");
	}

	@Override
	public Trainer selectById(int trainerIdx) {
		return sqlSessionTemplate.selectOne("Trainer.selectById", trainerIdx);
	}

	@Override
	public void insert(Trainer trainer) throws TrainerException {
		int result = sqlSessionTemplate.insert("Trainer.insert", trainer);
		if (result < 1) {
			throw new TrainerException("강사등록 실패");
		}
	}

	@Override
	public void update(Trainer trainer) throws TrainerException {

	}

	@Override
	public void delete(int trainerIdx) throws TrainerException {

	}

}
