package com.edu.surfing.model.shop;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.edu.surfing.domain.shop.Shop;
import com.edu.surfing.exception.CustomException;
import com.edu.surfing.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisShopDAO implements ShopDAO{
	private final SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<Shop> selectAll() {
		return sqlSessionTemplate.selectList("Shop.selectAll");
	}
	
	@Override
	public List<Shop> selectPopular() {
		return sqlSessionTemplate.selectList("Shop.selectPopular");
	}
	
	@Override
	public List<Shop> selectBySpot(String shopTown) {
		return sqlSessionTemplate.selectList("Shop.selectBySpot", shopTown);
	}

	@Override
	public Shop selectByIdx(int shopIdx) {
		return sqlSessionTemplate.selectOne("Shop.selectByIdx", shopIdx);
	}
	
	@Override
	public Shop selectByBusinessIdx(int businessIdx) {
		return sqlSessionTemplate.selectOne("Shop.selectByBusinessIdx", businessIdx);
	}

	@Override
	public void insert(Shop shop) throws CustomException {
		int result = sqlSessionTemplate.insert("Shop.insert", shop);
		if(result < 1) {
			throw new CustomException(ErrorCode.FAILED_REGIST);
		}
	}

	@Override
	public void update(Shop shop) throws CustomException {
		int result = sqlSessionTemplate.update("Shop.update", shop);
		if(result < 1) {
			throw new CustomException(ErrorCode.FAILED_UPDATE);
		}
	}

	@Override
	public void delete(int shopIdx) throws CustomException {
		
	}
	
}
