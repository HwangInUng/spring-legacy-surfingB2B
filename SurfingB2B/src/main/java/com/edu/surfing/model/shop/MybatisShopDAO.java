package com.edu.surfing.model.shop;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.surfing.domain.shop.Shop;
import com.edu.surfing.exception.ShopException;

@Repository
public class MybatisShopDAO implements ShopDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<Shop> selectAll() {
		return null;
	}

	@Override
	public Shop selectById(int shopIdx) {
		return null;
	}

	@Override
	public void insert(Shop shop) throws ShopException {
		int result = sqlSessionTemplate.insert("Shop.insert", shop);
		if(result < 1) {
			throw new ShopException("등록 실패");
		}
	}

	@Override
	public void update(Shop shop) {
		
	}

	@Override
	public void delete(int shopIdx) {
		
	}
	
}
