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
		return null;
	}

	@Override
	public Shop selectById(int shopIdx) {
		return null;
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
		
	}

	@Override
	public void delete(int shopIdx) throws CustomException {
		
	}
	
}
