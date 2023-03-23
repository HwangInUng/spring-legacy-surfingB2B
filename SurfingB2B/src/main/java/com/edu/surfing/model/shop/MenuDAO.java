package com.edu.surfing.model.shop;

import java.util.List;

import com.edu.surfing.domain.shop.Menu;

public interface MenuDAO {
	public List<Menu> selectAll(int shopIdx);
	public Menu select(int menuIdx);
	public void insert(Menu menu);
	public void update(Menu menu);
	public void delete(int menuIdx);
	public void deleteByShop(int shopIdx);
}
