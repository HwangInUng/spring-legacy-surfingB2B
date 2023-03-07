package com.edu.surfing.model.shop;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edu.surfing.domain.shop.ShopImage;
import com.edu.surfing.exception.ShopImageException;

@Repository
public class MybatisShopImageDAO implements ShopImageDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void insert(ShopImage shopImage) throws ShopImageException {
		int result = sqlSessionTemplate.insert("ShopImage.insert", shopImage);
		if (result < 1) {
			throw new ShopImageException("이미지 등록 실패");
		}
	}

}
