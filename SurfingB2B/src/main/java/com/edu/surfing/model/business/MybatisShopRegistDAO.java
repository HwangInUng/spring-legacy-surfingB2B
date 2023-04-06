package com.edu.surfing.model.business;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.edu.surfing.domain.business.ShopRegist;
import com.edu.surfing.exception.CustomException;
import com.edu.surfing.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisShopRegistDAO implements ShopRegistDAO{
	private final SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("ShopRegist.selectAll");
	}

	@Override
	public ShopRegist selectByIdx(int registIdx) {
		return sqlSessionTemplate.selectOne("ShopRegist.selectByIdx", registIdx);
	}

	@Override
	public void insert(ShopRegist shopRegist) throws CustomException {
		int result = sqlSessionTemplate.insert("ShopRegist.insert", shopRegist);
		if(result < 1) {
			throw new CustomException(ErrorCode.FAILED_REGIST, "입점신청");
		}
	}

	@Override
	public void delete(int registIdx) {
		int result = sqlSessionTemplate.delete("ShopRegist.delete", registIdx);
		if(result < 1) {
			throw new CustomException(ErrorCode.NOT_FOUND_DELETE);
		}
	}
	
	
}
