package com.edu.surfing.model.shop;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.surfing.domain.shop.Trainer;
import com.edu.surfing.exception.CustomException;
import com.edu.surfing.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisTrainerDAO implements TrainerDAO {
	private final SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<Trainer> selectByShop(int shopIdx) {
		return sqlSessionTemplate.selectList("Trainer.selectByShop", shopIdx);
	}

	@Override
	public Trainer select(int trainerIdx) {
		return sqlSessionTemplate.selectOne("Trainer.selectById", trainerIdx);
	}

	@Override
	public void insert(Trainer trainer) throws CustomException {
		int result = sqlSessionTemplate.insert("Trainer.insert", trainer);
		if (result < 1) {
			throw new CustomException(ErrorCode.FAILED_REGIST, "강사");
		}
	}

	@Override
	public void update(Trainer trainer) throws CustomException {

	}

	@Override
	public void delete(int trainerIdx) throws CustomException {
		int result = sqlSessionTemplate.delete("Trainer.delete", trainerIdx);
		if (result < 1) {
			throw new CustomException(ErrorCode.NOT_FOUND_DELETE);
		}
	}

}
