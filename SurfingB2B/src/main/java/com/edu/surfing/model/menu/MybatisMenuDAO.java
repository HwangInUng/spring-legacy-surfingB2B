package com.edu.surfing.model.menu;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.edu.surfing.domain.menu.Menu;
import com.edu.surfing.exception.CustomException;
import com.edu.surfing.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisMenuDAO implements MenuDAO {
	private final SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<Menu> selectAll() {
		return sqlSessionTemplate.selectList("Menu.selectAll");
	}

	@Override
	public Menu selectByShop(int shop_idx) {
		return sqlSessionTemplate.selectOne("Menu.selectByShop", shop_idx);
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
	public void delete(int menu_idx) throws CustomException {
		int result = sqlSessionTemplate.update("Menu.delete", menu_idx);
		if(result < 1) {
			throw new CustomException(ErrorCode.NOT_FOUND_DELETE);
		}
	}

	@Override
	public void deleteByShop(int shop_idx) throws CustomException {
		int result = sqlSessionTemplate.update("Menu.deleteByShop", shop_idx);
		if(result < 1) {
			throw new CustomException(ErrorCode.NOT_FOUND_DELETE);
		}
	}

}
