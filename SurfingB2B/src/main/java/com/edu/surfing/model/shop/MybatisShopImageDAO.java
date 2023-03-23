package com.edu.surfing.model.shop;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.edu.surfing.domain.shop.ShopImage;
import com.edu.surfing.exception.CustomException;
import com.edu.surfing.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisShopImageDAO implements ShopImageDAO {
	private final SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<String> selectByShop(int shopIdx) {
		return sqlSessionTemplate.selectList("ShopImage.selectByShop", shopIdx);
	}
	
	@Override
	public void insert(ShopImage shopImage) throws CustomException {
		int result = sqlSessionTemplate.insert("ShopImage.insert", shopImage);
		if (result < 1) {
			throw new CustomException(ErrorCode.INTERNAL_FILE_ERROR);
		}
	}
	
	@Override
	public void deleteByShop(int shopIdx) throws CustomException {
		int result = sqlSessionTemplate.delete("ShopImage.deleteByShop", shopIdx);
		if (result < 1) {
			throw new CustomException(ErrorCode.NOT_FOUND_DELETE);
		}
	}

}
