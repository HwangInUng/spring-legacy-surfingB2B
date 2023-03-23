package com.edu.surfing.model.shop;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.edu.surfing.domain.shop.Menu;
import com.edu.surfing.exception.CustomException;
import com.edu.surfing.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisMenuDAO implements MenuDAO {
	private final SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<Menu> selectAll(int shopIdx) {
		return sqlSessionTemplate.selectList("Menu.selectByShop", shopIdx);
	}

	@Override
	public Menu select(int menuIdx) {
		return sqlSessionTemplate.selectOne("Menu.select", menuIdx);
	}

	@Override
	public void insert(Menu menu) throws CustomException {
		int result = sqlSessionTemplate.insert("Menu.insert", menu);
		if(result < 1) {
			throw new CustomException(ErrorCode.FAILED_REGIST, "메뉴");
		}
	}

	@Override
	public void update(Menu menu) throws CustomException {
		int result = sqlSessionTemplate.update("Menu.update", menu);
		if(result < 1) {
			throw new CustomException(ErrorCode.FAILED_UPDATE, "메뉴");
		}
	}

	@Override
	public void delete(int menuIdx) throws CustomException {
		int result = sqlSessionTemplate.update("Menu.delete", menuIdx);
		if(result < 1) {
			throw new CustomException(ErrorCode.NOT_FOUND_DELETE);
		}
	}

	@Override
	public void deleteByShop(int shopIdx) throws CustomException {
		int result = sqlSessionTemplate.update("Menu.deleteByShop", shopIdx);
		if(result < 1) {
			throw new CustomException(ErrorCode.NOT_FOUND_DELETE);
		}
	}

}
