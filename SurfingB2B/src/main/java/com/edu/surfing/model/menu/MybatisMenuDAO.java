package com.edu.surfing.model.menu;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.edu.surfing.domain.menu.Menu;
import com.edu.surfing.exception.MenuException;

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
	public void insert(Menu menu) throws MenuException {
		int result = sqlSessionTemplate.insert("Menu.insert", menu);
		if(result < 1) {
			throw new MenuException("상품 등록 실패");
		}
	}

	@Override
	public void update(Menu menu) throws MenuException {
		int result = sqlSessionTemplate.update("Menu.update", menu);
		if(result < 1) {
			throw new MenuException("상품 수정 실패");
		}
	}

	@Override
	public void delete(int menu_idx) throws MenuException {
		int result = sqlSessionTemplate.update("Menu.delete", menu_idx);
		if(result < 1) {
			throw new MenuException("상품 삭제 실패");
		}
	}

	@Override
	public void deleteByShop(int shop_idx) throws MenuException {
		int result = sqlSessionTemplate.update("Menu.deleteByShop", shop_idx);
		if(result < 1) {
			throw new MenuException("해당샵의 상품 삭제 실패");
		}
	}

}
